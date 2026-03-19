package com.pharmacy.controller;

import com.pharmacy.model.*;
import com.pharmacy.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartRepository cartRepo;
    private final MedicineRepository medRepo;

    public CartController(CartRepository cartRepo, MedicineRepository medRepo) {
        this.cartRepo = cartRepo;
        this.medRepo = medRepo;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";

        Medicine med = medRepo.findById(id).orElse(null);
        if (med == null) return "redirect:/user/medicines";

        // Check if medicine already in user's cart
        List<Cart> cartItems = cartRepo.findByUser(user);
        for (Cart c : cartItems) {
            if (c.getMedicine().getId() == id) {
                c.setQuantity(c.getQuantity() + 1);
                cartRepo.save(c);
                return "redirect:/cart/view";
            }
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setMedicine(med);
        cart.setQuantity(1);
        cartRepo.save(cart);

        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String viewCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";

        List<Cart> cartItems = cartRepo.findByUser(user);
        double total = cartItems.stream().mapToDouble(c -> c.getMedicine().getPrice() * c.getQuantity()).sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        return "cart"; 
    }

    @GetMapping("/increase/{id}")
    public String increaseQty(@PathVariable int id) {
        cartRepo.findById(id).ifPresent(c -> {
            c.setQuantity(c.getQuantity() + 1);
            cartRepo.save(c);
        });
        return "redirect:/cart/view";
    }

    @GetMapping("/decrease/{id}")
    public String decreaseQty(@PathVariable int id) {
        cartRepo.findById(id).ifPresent(c -> {
            if (c.getQuantity() > 1) {
                c.setQuantity(c.getQuantity() - 1);
                cartRepo.save(c);
            }
        });
        return "redirect:/cart/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) {
        cartRepo.deleteById(id);
        return "redirect:/cart/view";
    }
}
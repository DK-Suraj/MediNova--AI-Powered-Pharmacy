package com.pharmacy.controller;

import com.pharmacy.model.*;
import com.pharmacy.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDateTime;
@Controller
@RequestMapping("/cart/checkout")
public class CheckoutController {

    private final SaleRepository saleRepo;
    private final MedicineRepository medicineRepo;
    private final CartRepository cartRepo;

    public CheckoutController(SaleRepository saleRepo, MedicineRepository medicineRepo, CartRepository cartRepo) {
        this.saleRepo = saleRepo;
        this.medicineRepo = medicineRepo;
        this.cartRepo = cartRepo;
    }

    @GetMapping("")
    public String checkoutPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";

        List<Cart> cartItems = cartRepo.findByUser(user);
        if (cartItems.isEmpty()) return "redirect:/cart/view";

        double total = cartItems.stream().mapToDouble(c -> c.getMedicine().getPrice() * c.getQuantity()).sum();

        model.addAttribute("user", user); // He add karne garjeche aahe
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "checkout";
    }

    @PostMapping("/place")
    @Transactional // Transaction safe
    public String placeOrder(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";

        List<Cart> cartItems = cartRepo.findByUser(user);
        if (cartItems.isEmpty()) return "redirect:/cart/view";

        for (Cart c : cartItems) {
            Medicine medicine = c.getMedicine();
            
            if (medicine.getQuantity() >= c.getQuantity()) {
                Sale sale = new Sale();
                sale.setMedicine(medicine);
                sale.setQuantity(c.getQuantity());
                sale.setUser(user);
                sale.setPhone(user.getPhone());
                sale.setAddress(user.getAddress());
                sale.setSaleType(SaleType.ONLINE);
                
                // Calculate and set total price to avoid the NOT NULL error
                sale.setTotalPrice(medicine.getPrice() * c.getQuantity());
                
                // Set the date using LocalDateTime
                sale.setSaleDate(java.time.LocalDateTime.now());
                
                // Set customer name (if your Sale model requires it)
                sale.setCustomerName(user.getName());

                // Reduce stock
                medicine.setQuantity(medicine.getQuantity() - c.getQuantity());
                medicineRepo.save(medicine);

                // Save order
                saleRepo.save(sale);
            }
            // Remove from cart after processing
            cartRepo.delete(c);
        }

        return "redirect:/user/orders"; 
    }}
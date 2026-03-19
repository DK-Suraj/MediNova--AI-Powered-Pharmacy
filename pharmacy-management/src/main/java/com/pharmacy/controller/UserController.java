package com.pharmacy.controller;

import com.pharmacy.model.Cart;
import com.pharmacy.model.Medicine;
import com.pharmacy.model.Sale;
import com.pharmacy.model.User;
import com.pharmacy.repository.CartRepository;
import com.pharmacy.repository.MedicineRepository;
import com.pharmacy.repository.SaleRepository;
import com.pharmacy.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepo;
    private final MedicineRepository medRepo;
    private final SaleRepository saleRepo;
    private final CartRepository cartRepo;

    // Constructor Injection for all required repositories
    public UserController(UserRepository userRepo, MedicineRepository medRepo, 
                          SaleRepository saleRepo, CartRepository cartRepo) {
        this.userRepo = userRepo;
        this.medRepo = medRepo;
        this.saleRepo = saleRepo;
        this.cartRepo = cartRepo;
    }

    // ================= 🔍 NORMAL SEARCH =================
    @GetMapping("/search")
    public String searchMedicine(@RequestParam String keyword, HttpSession session, Model model){
        if(session.getAttribute("user") == null){
            return "redirect:/user/login";
        }
        model.addAttribute("medicines", medRepo.findByNameContainingIgnoreCase(keyword));
        return "user_medicine_list";
    }

    // ================= 🤖 AI SEARCH =================
    @GetMapping("/ai-search")
    @ResponseBody
    public List<Medicine> aiSearch(@RequestParam String query){
        if(query == null || query.isEmpty()){
            return medRepo.findAll();
        }
        return medRepo.findByNameContainingIgnoreCase(query);
    }

    // ================= USER REGISTRATION =================
    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "user_register";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user){
        userRepo.save(user); // Phone & address are saved automatically via model binding
        return "redirect:/user/login";
    }

    // ================= USER LOGIN =================
    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("user", new User());
        return "user_login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model){
        User existing = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if(existing != null){
            session.setAttribute("user", existing);
            return "redirect:/user/home";
        } else {
            model.addAttribute("error", "Invalid Email or Password");
            return "user_login";
        }
    }

    // ================= USER HOME =================
    @GetMapping("/home")
    public String home(HttpSession session){
        if(session.getAttribute("user") == null){
            return "redirect:/user/login";
        }
        return "user_home";
    }

    // ================= VIEW ALL MEDICINES =================
    @GetMapping("/medicines")
    public String userMedicines(HttpSession session, Model model){
        if(session.getAttribute("user") == null){
            return "redirect:/user/login";
        }
        model.addAttribute("medicines", medRepo.findAll());
        return "user_medicine_list";
    }

    // ================= 📦 MY ORDERS (NEWLY ADDED) =================
    @GetMapping("/orders")
    public String myOrders(HttpSession session, Model model) {
        // Retrieve the logged-in user from the session
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/user/login";
        }

        // Fetch the list of orders for this specific user
        List<Sale> userOrders = saleRepo.findByUser(user);

        model.addAttribute("orders", userOrders);
        model.addAttribute("user", user);

        return "orders"; // Returns orders.html
    }

    // ================= USER PROFILE =================
    @GetMapping("/profile")
    public String userProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";

        model.addAttribute("user", user);
        return "profile"; // Returns profile.html
    }

    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate(); // Clear session data
        return "redirect:/user/login";
    }
}
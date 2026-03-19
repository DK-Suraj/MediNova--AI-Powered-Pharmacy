package com.pharmacy.controller;

import com.pharmacy.model.Admin;
import com.pharmacy.repository.AdminRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class AdminController {

    private final AdminRepository adminRepo;

    public AdminController(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    @GetMapping("/admin/login")
    public String loginPage(Model model) {
        // Create empty Admin object for Thymeleaf form binding
        model.addAttribute("admin", new Admin());
        return "admin_login";
    }

    // Admin Login Handling - POST
    @PostMapping("/admin/login")
    public String login(@Valid @ModelAttribute Admin admin,
                        BindingResult result,
                        HttpSession session,
                        Model model) {

        if (result.hasErrors()) {
            return "admin_login"; // show login again if validation fails
        }

        // Check username & password in DB
        Admin existingAdmin = adminRepo.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());

        if (existingAdmin != null) {
            session.setAttribute("admin", existingAdmin); // store admin in session
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "admin_login";
        }
    }

    // Admin Dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login"; // if not logged in, redirect
        }
        return "dashboard"; // show dashboard.html template
    }

    // Admin Logout
    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
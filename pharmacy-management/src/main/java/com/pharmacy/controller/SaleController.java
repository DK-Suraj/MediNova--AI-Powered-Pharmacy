package com.pharmacy.controller;

import com.pharmacy.model.Medicine;
import com.pharmacy.model.Sale;
import com.pharmacy.model.SaleType;
import com.pharmacy.model.User;
import com.pharmacy.repository.MedicineRepository;
import com.pharmacy.repository.SaleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/sale") // Base path for all sale-related routes
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private MedicineRepository medicineRepo; // Needed to fetch medicines for the dropdown

    // ================= 1. ADMIN VIEW: ALL SALES =================
    @GetMapping("/list")
    public String viewAllSales(Model model) {
        List<Sale> sales = saleRepository.findAll();
        model.addAttribute("sales", sales);
        return "sale_list"; 
    }

    // ================= 2. DELETE SALE =================
    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable int id) {
        saleRepository.deleteById(id);
        return "redirect:/sale/list";
    }

    // ================= 3. ADD SALE (DISPLAY FORM) =================
    @GetMapping("/add")
    public String showSaleForm(Model model) {
        model.addAttribute("sale", new Sale());
        model.addAttribute("medicines", medicineRepo.findAll()); // Populate medicine dropdown
        return "sale_form"; 
    }

    // ================= 4. SAVE SALE (PROCESS FORM) =================
    @PostMapping("/save")
    @Transactional
    public String saveSale(@ModelAttribute("sale") Sale sale) {
        // Find the medicine to get the price and update stock
        Medicine med = medicineRepo.findById(sale.getMedicine().getId()).orElse(null);
        
        if (med != null && med.getQuantity() >= sale.getQuantity()) {
            // Deduct stock from the medicine table
            med.setQuantity(med.getQuantity() - sale.getQuantity());
            medicineRepo.save(med);
            
            // Set required sale details
            sale.setTotalPrice(med.getPrice() * sale.getQuantity());
            sale.setSaleDate(LocalDateTime.now());
            sale.setSaleType(SaleType.OFFLINE); // Admin manual sales are OFFLINE
            
            saleRepository.save(sale);
            return "redirect:/sale/list";
        } else {
            // Redirect back with an error if stock is insufficient
            return "redirect:/sale/add?error=outofstock";
        }
    }

    // ================= 5. USER VIEW: ORDER HISTORY =================
    @GetMapping("/my-orders")
    public String viewUserSales(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }

        List<Sale> sales = saleRepository.findByUser(user);
        model.addAttribute("sales", sales);
        model.addAttribute("user", user);
        return "orders"; 
    }

    // ================= 6. SEARCH SALES (ADMIN) =================
    @GetMapping("/search")
    public String searchSales(@RequestParam String keyword, Model model) {
        List<Sale> sales = saleRepository.findByCustomerNameContainingIgnoreCase(keyword);
        model.addAttribute("sales", sales);
        return "sale_list";
    }
}
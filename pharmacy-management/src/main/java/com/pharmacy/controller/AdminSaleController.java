package com.pharmacy.controller;

import com.pharmacy.model.*;
import com.pharmacy.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/sale")
public class AdminSaleController {

    private final SaleRepository saleRepo;
    private final MedicineRepository medicineRepo;

    public AdminSaleController(SaleRepository saleRepo, MedicineRepository medicineRepo){
        this.saleRepo = saleRepo;
        this.medicineRepo = medicineRepo;
    }

    @GetMapping("/add")
    public String addSalePage(Model model){
        model.addAttribute("medicines", medicineRepo.findAll());
        return "admin_add_sale";
    }

    @PostMapping("/add")
    public String addSale(@RequestParam int medicineId,
                          @RequestParam int quantity,
                          @RequestParam String customerName,
                          @RequestParam String phone,
                          @RequestParam String address){

        Medicine medicine = medicineRepo.findById(medicineId).orElse(null);
        if(medicine == null) return "redirect:/admin/sale/add";

        Sale sale = new Sale();
        sale.setMedicine(medicine);
        sale.setQuantity(quantity);
        sale.setCustomerName(customerName);
        sale.setPhone(phone);
        sale.setAddress(address);
        sale.setSaleType(SaleType.OFFLINE);

        // reduce stock
        medicine.setQuantity(medicine.getQuantity() - quantity);
        medicineRepo.save(medicine);

        saleRepo.save(sale);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/list")
    public String saleList(Model model){
        model.addAttribute("sales", saleRepo.findAll());
        return "admin_sale_list";
    }
}
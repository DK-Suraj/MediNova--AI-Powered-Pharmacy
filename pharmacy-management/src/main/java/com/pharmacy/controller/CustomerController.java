package com.pharmacy.controller;

import com.pharmacy.model.Customer;
import com.pharmacy.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository custRepo;

    public CustomerController(CustomerRepository custRepo) {
        this.custRepo = custRepo;
    }

    @GetMapping("/list")
    public String listCustomers(Model model){
        model.addAttribute("customers", custRepo.findAll());
        return "customer_list";
    }

    @GetMapping("/add")
    public String addCustomerForm(Model model){
        model.addAttribute("customer", new Customer());
        return "customer_form";
    }

    @PostMapping("/save")
    public String saveCustomer(@Valid @ModelAttribute Customer customer,
                               BindingResult result){
        if(result.hasErrors()){
            return "customer_form";
        }
        custRepo.save(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable int id, Model model){
        Customer cust = custRepo.findById(id).orElse(null);
        if(cust == null) return "redirect:/customer/list";
        model.addAttribute("customer", cust);
        return "customer_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id){
        custRepo.deleteById(id);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomer(@RequestParam String keyword, Model model){
        model.addAttribute("customers", custRepo.findByNameContainingIgnoreCase(keyword));
        return "customer_list";
    }
}
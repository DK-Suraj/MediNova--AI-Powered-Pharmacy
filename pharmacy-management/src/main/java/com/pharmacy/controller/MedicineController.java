package com.pharmacy.controller;

import com.pharmacy.model.Medicine;
import com.pharmacy.repository.MedicineRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineRepository medRepo;

    public MedicineController(MedicineRepository medRepo) {
        this.medRepo = medRepo;
    }

    @GetMapping("/list")
    public String listMedicines(Model model){
        model.addAttribute("medicines", medRepo.findAll()); // <- Correct
        return "medicine_list";
    }

    @GetMapping("/add")
    public String addMedicineForm(Model model){
        model.addAttribute("medicine", new Medicine());
        return "medicine_form";
    }

    @PostMapping("/save")
    public String saveMedicine(@Valid @ModelAttribute Medicine medicine,
                               BindingResult result){
        if(result.hasErrors()){
            return "medicine_form";
        }
        medRepo.save(medicine);
        return "redirect:/medicine/list";
    }

    @GetMapping("/edit/{id}")
    public String editMedicine(@PathVariable int id, Model model){
        model.addAttribute("medicine", medRepo.findById(id).get());
        return "medicine_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable int id){
        medRepo.deleteById(id);
        return "redirect:/medicine/list";
    }

    @GetMapping("/search")
    public String searchMedicine(@RequestParam String keyword, Model model){
        model.addAttribute("medicines", medRepo.findByNameContainingIgnoreCase(keyword));
        return "medicine_list";
    }
}
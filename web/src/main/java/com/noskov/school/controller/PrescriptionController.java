package com.noskov.school.controller;

import com.noskov.school.dto.PrescriptionDTO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.service.api.PrescriptionService;
import com.noskov.school.service.api.ProcAndMedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    ProcAndMedService procAndMedService;

    @GetMapping("/add_page")
    public String addPrescription(Model model){
        model.addAttribute("prescription", new PrescriptionDTO());
        model.addAttribute("medicines",procAndMedService.getAllMedicines());
        model.addAttribute("procedures",procAndMedService.getAllProcedures());
        return "prescription/add_page";
    }

    @PostMapping("/add_page")
    public String addPrescription(@ModelAttribute("prescription") PrescriptionDTO prescription){
        prescriptionService.add(prescription);
        return "patient/profile";
    }
}

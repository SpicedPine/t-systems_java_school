package com.noskov.school.controller;

import com.noskov.school.dto.PrescriptionDTO;
import com.noskov.school.service.api.PrescriptionService;
import com.noskov.school.service.api.ProcAndMedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController {

    //fixme modificator ?
    //fixme write UT (Andrey: field injection)
    @Autowired
    PrescriptionService prescriptionService;

    //todo unused import ?
    @Autowired
    ProcAndMedService procAndMedService;

    @GetMapping("/add_page")
    public String addPrescription(){
        return "prescription/add_page";
    }

    @PostMapping("")
    public String addPrescription(@ModelAttribute("prescription") PrescriptionDTO prescription){
        prescriptionService.add(prescription);
        return "redirect:patient/profile";
    }
}

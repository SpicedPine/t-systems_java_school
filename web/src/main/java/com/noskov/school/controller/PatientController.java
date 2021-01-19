package com.noskov.school.controller;


import com.noskov.school.service.api.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("")
    public String allPatients(Model model){
        model.addAttribute("patients", patientService.getAll());
        return "patient/patients";
    }

}

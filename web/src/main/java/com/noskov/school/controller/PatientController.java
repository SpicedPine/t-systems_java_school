package com.noskov.school.controller;


import com.noskov.school.dto.PrescriptionDTO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.service.api.PatientService;
import com.noskov.school.service.api.PrescriptionService;
import com.noskov.school.service.api.ProcAndMedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    ProcAndMedService procAndMedService;

    @GetMapping("")
    public String allPatients(Model model){
        model.addAttribute("patients", patientService.getAll());
        return "patient/patients";
    }

    @GetMapping("/add")
    public String addPatient(Model model){
        model.addAttribute("patient", new PatientPO());
        return "patient/add";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute(name = "patient") PatientPO patient){
        patientService.add(patient);
        return "patient/patients";
    }

    @PostMapping("/{id}/release")
    public String releasePatient(@RequestParam("id") Long id){
        patientService.delete(id);
        return "patient/patients";
    }

    @GetMapping("/{id}")
    public String getPatientProfile(@PathVariable("id") Long id, Model model){
        model.addAttribute("patient", patientService.getOne(id));
        model.addAttribute("prescriptions",patientService.getOne(id).getPrescriptionList());
        return "patient/profile";
    }

    @GetMapping("/profile/{id}")
    public ModelAndView getPatientPrescription(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("prescription/edit_page");
        modelAndView.addObject("prescription",prescriptionService.getOne(id));
        modelAndView.addObject("medicines",procAndMedService.getAllMedicines());
        modelAndView.addObject("procedures",procAndMedService.getAllProcedures());
        return modelAndView;
    }
}

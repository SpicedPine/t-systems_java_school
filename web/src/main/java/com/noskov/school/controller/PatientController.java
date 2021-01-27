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
        return "redirect:/patient/";
    }

    @GetMapping("/{id}/release")
    public String releasePatient(@PathVariable("id") Long id){
        patientService.delete(id);
        return "redirect:/patient/";
    }

    @GetMapping("/{patientId}")
    public String getPatientProfile(@PathVariable("patientId") Long id, Model model){
        model.addAttribute("patient", patientService.getOne(id));
        model.addAttribute("prescriptions",patientService.getOne(id).getPrescriptionList());
        return "patient/profile";
    }

    @GetMapping("/profile/{prescriptionId}")
    public ModelAndView getPatientPrescription(@PathVariable("prescriptionId") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("prescription/edit_page");
        modelAndView.addObject("prescription",prescriptionService.getOne(id));
        modelAndView.addObject("medicines",procAndMedService.getAllMedicines());
        modelAndView.addObject("procedures",procAndMedService.getAllProcedures());
        return modelAndView;
    }

    @GetMapping("/{patientId}/add_prescription")
    public ModelAndView addPrescription(@PathVariable("patientId") Long id){
        PatientPO patient = patientService.getOne(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("patient", patient);
        modelAndView.addObject("prescription", new PrescriptionDTO());
        modelAndView.addObject("medicines",procAndMedService.getAllMedicines());
        modelAndView.addObject("procedures",procAndMedService.getAllProcedures());
        modelAndView.setViewName("prescription/add_page");
        return modelAndView;
    }
}

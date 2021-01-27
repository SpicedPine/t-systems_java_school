package com.noskov.school.controller;


import com.noskov.school.dto.PrescriptionDTO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.service.api.*;
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

    @Autowired
    EventGenerationService eventGenerationService;

    @Autowired
    EventService eventService;

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

    @GetMapping("/{patientId}/medicine/{prescriptionId}")
    public ModelAndView getPatientMedicinePrescription(@PathVariable("patientId") Long patientId,
                                                       @PathVariable("prescriptionId") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("prescription/medicine_edit_page");
        modelAndView.addObject("prescription",prescriptionService.getOne(id));
        modelAndView.addObject("medicines",procAndMedService.getAllMedicines());
        modelAndView.addObject("procedures",procAndMedService.getAllProcedures());
        return modelAndView;
    }

    @PostMapping("/{patientId}/medicine/{prescriptionId}/edit_medicine")
    public String editMedicinePrescription(@PathVariable("patientId") Long patientId,
                                   @PathVariable("prescriptionId") Long prescriptionId,
                                   @ModelAttribute PrescriptionDTO prescriptionDTO) throws Exception {
        PatientPO patientPO = patientService.getOne(patientId);
        String therapyName = prescriptionDTO.getScratch().getTypeTherapyName();
        ProcedureAndMedicinePO therapy = procAndMedService.getByName(therapyName);
        eventService.deleteByPatientAndTherapy(patientPO,therapy);
        prescriptionDTO.setPatient(patientPO);
        prescriptionDTO.setProcOrMedicine(procAndMedService.getByName(therapyName));
        PrescriptionPO prescriptionPO= prescriptionService.convertToPO(prescriptionDTO);
        eventGenerationService.generateEvents(prescriptionPO);

        prescriptionService.update(prescriptionDTO, prescriptionId);
        return "redirect:/patient/{patientId}";
    }

    @GetMapping("/{patientId}/procedure/{prescriptionId}")
    public ModelAndView getPatientProcedurePrescription(@PathVariable("patientId") Long patientId,
                                                       @PathVariable("prescriptionId") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("prescription/procedure_edit_page");
        modelAndView.addObject("prescription",prescriptionService.getOne(id));
        modelAndView.addObject("medicines",procAndMedService.getAllMedicines());
        modelAndView.addObject("procedures",procAndMedService.getAllProcedures());
        return modelAndView;
    }

    @PostMapping("/{patientId}/procedure/{prescriptionId}/edit_procedure")
    public String editProcedurePrescription(@PathVariable("patientId") Long patientId,
                                            @PathVariable("prescriptionId") Long prescriptionId,
                                            @ModelAttribute PrescriptionDTO prescriptionDTO) throws Exception {
        PatientPO patientPO = patientService.getOne(patientId);
        ProcedureAndMedicinePO oldProc = prescriptionService.getOne(prescriptionId).getProcOrMedicine();
        String therapyName = prescriptionDTO.getScratch().getTypeTherapyName();
        eventService.deleteByPatientAndTherapy(patientPO,oldProc);
        prescriptionDTO.setPatient(patientPO);
        prescriptionDTO.setProcOrMedicine(procAndMedService.getByName(therapyName));
        PrescriptionPO prescriptionPO= prescriptionService.convertToPO(prescriptionDTO);
        eventGenerationService.generateEvents(prescriptionPO);

        prescriptionService.update(prescriptionDTO, prescriptionId);
        return "redirect:/patient/{patientId}";
    }

    @GetMapping("/{patientId}/add_prescription")
    public ModelAndView addPrescription(@PathVariable("patientId") Long id){
        PatientPO patient = patientService.getOne(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("patient", patient);
        modelAndView.addObject("prescription", new PrescriptionDTO(patient));
        modelAndView.addObject("medicines",procAndMedService.getAllMedicines());
        modelAndView.addObject("procedures",procAndMedService.getAllProcedures());
        modelAndView.setViewName("prescription/add_page");
        return modelAndView;
    }

    @PostMapping("/{patientId}/add_page")
    public String addPrescription(@PathVariable("patientId") Long id,
                                  @ModelAttribute PrescriptionDTO prescription) throws Exception {
        prescription.setPatient(patientService.getOne(id));
        String name = prescription.getScratch().getTypeTherapyName();
        prescription.setProcOrMedicine(procAndMedService.getByName(name));
        PrescriptionPO prescriptionPO= prescriptionService.convertToPO(prescription);
        eventGenerationService.generateEvents(prescriptionPO);

        prescriptionService.add(prescription);
        return "redirect:/patient/{patientId}";
    }

    @GetMapping("/{patientId}/cancel/{prescriptionId}")
    public String cancelPrescription(@PathVariable("patientId") Long patientId,
                                     @PathVariable("prescriptionId") Long prescriptionId){
        prescriptionService.delete(prescriptionId);
        return "redirect:/patient/{patientId}";
    }
}

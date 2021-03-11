package com.noskov.school.controller;


import com.noskov.school.dto.PrescriptionDTO;
import com.noskov.school.persistent.MedicalStaffPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.service.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    private final EventService eventService;

    private final PatientService patientService;

    private final PrescriptionService prescriptionService;

    private final ProcAndMedService procAndMedService;

    private final MedicalStaffService medicalStaffService;

    @Autowired
    public PatientController(EventService eventService, PatientService patientService, PrescriptionService prescriptionService, ProcAndMedService procAndMedService, MedicalStaffService medicalStaffService) {
        this.eventService = eventService;
        this.patientService = patientService;
        this.prescriptionService = prescriptionService;
        this.procAndMedService = procAndMedService;
        this.medicalStaffService = medicalStaffService;
    }

    @GetMapping("")
    public String allPatients(Model model){
        MedicalStaffPO physician = (MedicalStaffPO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("patients", physician.getPatients());
        return "patient/patients";
    }

    @GetMapping("/add")
    public String addPatient(Model model){
        model.addAttribute("patient", new PatientPO());
        return "patient/add";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute(name = "patient") PatientPO patient){
        MedicalStaffPO physician = (MedicalStaffPO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        LOGGER.info("Trying to add patient to physician with email:{}", physician.getEmail());

        patientService.addIfNotExist(patient);
        medicalStaffService.addPatientToPhysician(patient, physician);

        return "redirect:/patient/";
    }

    @GetMapping("/{id}/release")
    public String releasePatient(@PathVariable("id") Long id){
        LOGGER.info("Trying to release patient with id = {}", id);

        patientService.release(id);
        return "redirect:/patient/";
    }

    @GetMapping("/{patientId}")
    public String getPatientProfile(@PathVariable("patientId") Long id, Model model){
        model.addAttribute("patientProfile",patientService.getPatientProfile(id));
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
        LOGGER.info("Trying to edit medicine prescription");

        prescriptionService.editPrescription(patientId, prescriptionId, prescriptionDTO);
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
        LOGGER.info("Trying to edit procedure prescription");

        prescriptionService.editPrescription(patientId, prescriptionId, prescriptionDTO);
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
        LOGGER.info("Trying to add prescription");

        prescriptionService.add(prescription, id);
        return "redirect:/patient/{patientId}";
    }

    @GetMapping("/{patientId}/cancel/{prescriptionId}")
    public String cancelPrescription(@PathVariable("patientId") Long patientId,
                                     @PathVariable("prescriptionId") Long prescriptionId){
        LOGGER.info("Trying to cancel prescription");

        eventService.cancelFromNowByPatientAndPrescription(patientId, prescriptionId);
        prescriptionService.delete(prescriptionId);
        return "redirect:/patient/{patientId}";
    }
}

package com.noskov.school.controller;

import com.noskov.school.persistent.MedicalStaffPO;
import com.noskov.school.service.api.MedicalStaffService;
import com.noskov.school.service.api.StaffPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nurse")
public class NurseRegistrationController {
    private final MedicalStaffService medicalStaffService;

    private final StaffPostService staffPostService;

    @Autowired
    public NurseRegistrationController(MedicalStaffService medicalStaffService, StaffPostService staffPostService) {
        this.medicalStaffService = medicalStaffService;
        this.staffPostService = staffPostService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("staff", new MedicalStaffPO());
        model.addAttribute("staffPosts", staffPostService.getAllPosts());
        return "nurse/registration";
    }

    @PostMapping("/registration")
    public String signUp(@ModelAttribute(name = "staff") MedicalStaffPO staff, Model model) {

        if (!medicalStaffService.saveNurse(staff)){
            model.addAttribute("usernameError", "Nurse with provided email already exist");
            return "nurse/registration";
        }

        if (!staff.getPassword().equals(staff.getPasswordConfirm())){
            model.addAttribute("passwordError", "Passwords doesn't match");
            return "nurse/registration";
        }


        medicalStaffService.saveNurse(staff);
        return "redirect:/";
    }
}

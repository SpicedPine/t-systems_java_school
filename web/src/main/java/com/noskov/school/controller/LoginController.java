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
@RequestMapping("/")
public class LoginController {
    @Autowired
    MedicalStaffService medicalStaffService;

    @Autowired
    StaffPostService staffPostService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("staff", new MedicalStaffPO());
        return "/login";
    }

    /*@PostMapping("/login")
    public String login(@ModelAttribute("staff") MedicalStaffPO staff){
        staff = medicalStaffService.getByEmailAndPassword(staff.getEmail());
        if(staff.getAuthorities().contains(staffPostService.getPhysician())){
            return "redirect:event/";
        } else if (staff.getAuthorities().contains(staffPostService.getNurse())){
            return "redirect:patient/";
        } else throw new RuntimeException("No staff post during logging");
    }*/
}

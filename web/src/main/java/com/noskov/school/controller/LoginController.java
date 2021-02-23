package com.noskov.school.controller;

import com.noskov.school.persistent.MedicalStaffPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(@RequestParam(name = "fail", required = false) Boolean fail, Model model){
        LOGGER.info("login page is uploaded");

        if(Boolean.TRUE.equals(fail)){
            model.addAttribute("login_fail", fail);
        }
        model.addAttribute("staff", new MedicalStaffPO());
        return "/login";
    }
}

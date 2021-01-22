package com.noskov.school.controller;

import com.noskov.school.service.api.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;
}

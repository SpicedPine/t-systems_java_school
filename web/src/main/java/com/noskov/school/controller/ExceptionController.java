package com.noskov.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exceptions")
public class ExceptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @GetMapping("/forbidden_page")
    public String returnForbiddenPage(){
        LOGGER.info("There was try to get forbidden page");

        return "exceptions/forbidden_page";
    }
}

package com.noskov.school.controller;

import com.noskov.school.service.api.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/")
    public String allEvents(Model model){
        model.addAttribute("events", eventService.getAll());
        model.addAttribute("eventsForHour", eventService.getEventsForHour());
        model.addAttribute("eventsForDay", eventService.getEventsForDay());
        return "event/events";
    }

}

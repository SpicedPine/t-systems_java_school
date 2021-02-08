package com.noskov.school.controller;

import com.noskov.school.persistent.EventPO;
import com.noskov.school.service.api.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/event")
public class EventController {

    //todo modificator
    @Autowired
    EventService eventService;

    @GetMapping("/")
    public String allEvents(Model model){
        model.addAttribute("events", eventService.getAll());
        return "event/events";
    }

    @GetMapping("/for_hour")
    public String eventsForHour(Model model){
        model.addAttribute("eventsForHour", eventService.getEventsForHour());
        return "/event/events_for_hour";
    }

    @GetMapping("/for_day")
    public String eventsForDay(Model model){
        model.addAttribute("eventsForDay", eventService.getEventsForDay());
        return "/event/events_for_day";
    }

    @GetMapping("/{eventId}/changeToDone")
    public String doEvent(@PathVariable("eventId") Long eventId){
        eventService.changeStatusToDone(eventId);
        return "redirect:/event/";
    }

    @GetMapping("/{eventId}/changeToCancelled")
    public ModelAndView doCancel(@PathVariable("eventId") Long eventId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("event",eventService.getOne(eventId));
        modelAndView.setViewName("event/why_cancelled");
        eventService.changeStatusToCancelled(eventId);
        return modelAndView;
    }

    @PostMapping("/{eventId}/changeToCancelled")
    public String doCancel(@ModelAttribute EventPO eventPO,
                           @PathVariable("eventId") Long eventId){
        //todo business logic
        EventPO oldEvent = eventService.getOne(eventId);
        String reason = eventPO.getReasonToCancel();
        oldEvent.setReasonToCancel(reason);
        eventService.update(oldEvent);

        return "redirect:/event/";
    }
}

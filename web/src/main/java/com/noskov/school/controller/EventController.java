package com.noskov.school.controller;

import com.noskov.school.dto.InnerEventDTO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.service.api.EventScheduleService;
import com.noskov.school.service.api.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/event")
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    private final EventScheduleService eventScheduleService;

    @Autowired
    public EventController(EventService eventService, EventScheduleService eventScheduleService) {
        this.eventService = eventService;
        this.eventScheduleService = eventScheduleService;
    }

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
        LOGGER.info("Changing event status to DONE for event with id = {}", eventId);

        eventService.changeStatus(eventId,EventStatus.DONE);
        eventScheduleService.updateSchedule();
        return "redirect:/event/";
    }

    @GetMapping("/{eventId}/changeToCancelled")
    public ModelAndView doCancel(@PathVariable("eventId") Long eventId){
        LOGGER.info("Changing event status to CANCEL for event with id = {}", eventId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("event",eventService.getOne(eventId));
        modelAndView.setViewName("event/why_cancelled");
        eventService.changeStatus(eventId, EventStatus.CANCELED);
        return modelAndView;
    }

    @PostMapping("/{eventId}/changeToCancelled")
    public String doCancel(@ModelAttribute InnerEventDTO eventPO,
                           @PathVariable("eventId") Long eventId){
        LOGGER.info("Setting reason to cancel event with id = {}", eventId);

        eventService.setReasonToCancel(eventPO.getReasonToCancel(), eventId);
        eventScheduleService.updateSchedule();
        return "redirect:/event/";
    }
}

package com.noskov.school.controller;

import com.noskov.school.dto.EventDTO;
import com.noskov.school.service.api.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

    private final EventService eventService;

    @Autowired
    public ScheduleController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<EventDTO> events(){
        LOGGER.info("External get request for events on today");

        List<EventDTO> eventDTOList = eventService.getEventsForDayExternal();
        return eventDTOList;
    }
}

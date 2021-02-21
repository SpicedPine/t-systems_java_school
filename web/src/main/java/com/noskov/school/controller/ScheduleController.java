package com.noskov.school.controller;

import com.noskov.school.dto.EventDTO;
import com.noskov.school.service.api.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final EventService eventService;

    @Autowired
    public ScheduleController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<EventDTO> events(){
        List<EventDTO> eventDTOList = eventService.getEventsForDayExternal();
        return eventDTOList;
    }
}

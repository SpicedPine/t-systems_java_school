package com.noskov.school.converters;

import com.noskov.school.dto.EventDTO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.persistent.EventPO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EventServiceConverter {
    public EventDTO convertToDTO(EventPO eventPO){
        String patientFirstName = eventPO.getPatient().getFirstName();
        String patientLastName = eventPO.getPatient().getLastName();
        String dateTime = eventPO.getDateAndTime().toString();
        EventStatus eventStatus = eventPO.getStatus();
        String eventTypeName = eventPO.getEventType().getName();

        return new EventDTO(patientFirstName, patientLastName,
                dateTime, eventStatus, eventTypeName);
    }
}

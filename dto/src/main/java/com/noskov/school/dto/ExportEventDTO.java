package com.noskov.school.dto;

import com.noskov.school.enums.EventStatus;

import java.time.LocalDateTime;

public class ExportEventDTO extends AbstractEventDTO{
    public ExportEventDTO(String patientFirstName, String patientLastName, String dateTime, EventStatus eventStatus, String eventTypeName) {
        super(patientFirstName, patientLastName, dateTime, eventStatus, eventTypeName);
    }
}

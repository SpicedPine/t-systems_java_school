package com.noskov.school.converters;

import com.noskov.school.dto.ExportEventDTO;
import com.noskov.school.dto.InnerEventDTO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.persistent.EventPO;
import org.springframework.stereotype.Component;

@Component
public class EventServiceConverter {
    public ExportEventDTO convertToExportDTO(EventPO eventPO){
        String patientFirstName = eventPO.getPatient().getFirstName();
        String patientLastName = eventPO.getPatient().getLastName();
        String dateTime = eventPO.getDateAndTime().toString();
        EventStatus eventStatus = eventPO.getStatus();
        String eventTypeName = eventPO.getEventType().getName();

        return new ExportEventDTO(patientFirstName, patientLastName,
                dateTime, eventStatus, eventTypeName);
    }

    public InnerEventDTO convertToInnerDTO(EventPO eventPO){
        String patientFirstName = eventPO.getPatient().getFirstName();
        String patientLastName = eventPO.getPatient().getLastName();
        String dateTime = eventPO.getDateAndTime().toString();
        EventStatus eventStatus = eventPO.getStatus();
        String eventTypeName = eventPO.getEventType().getName();
        String reasonToCancel = eventPO.getReasonToCancel();
        String doseDescription = eventPO.getDoseDescription();
        long id = eventPO.getId();

        return new InnerEventDTO(patientFirstName, patientLastName,
                dateTime, eventStatus, eventTypeName, id, reasonToCancel, doseDescription);
    }
}

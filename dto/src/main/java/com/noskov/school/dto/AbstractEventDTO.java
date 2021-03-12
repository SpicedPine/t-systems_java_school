package com.noskov.school.dto;

import com.noskov.school.enums.EventStatus;

public abstract class AbstractEventDTO {
    private String patientFirstName;
    private String patientLastName;
    private String dateTime;
    private EventStatus eventStatus;
    private String eventTypeName;

    public AbstractEventDTO(String patientFirstName,
                          String patientLastName, String dateTime,
                          EventStatus eventStatus, String eventTypeName) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.dateTime = dateTime;
        this.eventStatus = eventStatus;
        this.eventTypeName = eventTypeName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }
}

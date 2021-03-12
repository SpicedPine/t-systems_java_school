package com.noskov.school.dto;

import com.noskov.school.enums.EventStatus;

public class InnerEventDTO extends AbstractEventDTO{
    private long id;
    private String reasonToCancel;

    public InnerEventDTO(String patientFirstName, String patientLastName, String dateTime, EventStatus eventStatus, String eventTypeName, long id, String reasonToCancel, String doseDescription) {
        super(patientFirstName, patientLastName, dateTime, eventStatus, eventTypeName);
        this.id = id;
        this.reasonToCancel = reasonToCancel;
        this.doseDescription = doseDescription;
    }

    private String doseDescription;


    public InnerEventDTO(String patientFirstName, String patientLastName, String dateTime, EventStatus eventStatus, String eventTypeName) {
        super(patientFirstName, patientLastName, dateTime, eventStatus, eventTypeName);
    }

    public long getId() {
        return id;
    }

    public String getReasonToCancel() {
        return reasonToCancel;
    }

    public void setReasonToCancel(String reasonToCancel) {
        this.reasonToCancel = reasonToCancel;
    }

    public String getDoseDescription() {
        return doseDescription;
    }

    public void setDoseDescription(String doseDescription) {
        this.doseDescription = doseDescription;
    }
}

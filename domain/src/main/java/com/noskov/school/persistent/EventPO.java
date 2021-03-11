package com.noskov.school.persistent;

import com.noskov.school.enums.EventStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class EventPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reason_to_cancel", nullable = true)
    private String reasonToCancel;

    @Column(name = "dose_description", nullable = true)
    private String doseDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id",nullable = false)
    private PatientPO patient;

    @Column(name = "date_and_time",  nullable = false)
    private LocalDateTime dateAndTime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proc_or_med_id",nullable = false)
    private ProcedureAndMedicinePO eventType;

    public EventPO(PatientPO patient, LocalDateTime dateAndTime, EventStatus status, ProcedureAndMedicinePO eventType) {
        this.patient = patient;
        this.dateAndTime = dateAndTime;
        this.status = status;
        this.eventType = eventType;
    }

    public EventPO() {

    }

    public Long getId() {
        return id;
    }

    public PatientPO getPatient() {
        return patient;
    }

    public void setPatient(PatientPO patient) {
        this.patient = patient;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public ProcedureAndMedicinePO getEventType() {
        return eventType;
    }

    public void setEventType(ProcedureAndMedicinePO eventType) {
        this.eventType = eventType;
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

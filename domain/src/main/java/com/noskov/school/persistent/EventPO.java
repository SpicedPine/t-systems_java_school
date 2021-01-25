package com.noskov.school.persistent;

import com.noskov.school.enums.EventStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EVENTS")
public class EventPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID",nullable = false)
    private PatientPO patient;

    @Column(name = "DATE_AND_TIME",  nullable = false)
    private LocalDateTime dateAndTime;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne
    @JoinColumn(name = "PROC_OR_MED_ID",nullable = false)
    private ProcedureAndMedicinePO eventType;

    public EventPO(PatientPO patient, LocalDateTime dateAndTime, EventStatus status, ProcedureAndMedicinePO eventType) {
        this.patient = patient;
        this.dateAndTime = dateAndTime;
        this.status = status;
        this.eventType = eventType;
    }

    public EventPO() {

    }
}

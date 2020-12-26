package com.noskov.school.persistant;

import com.noskov.school.utils.EventStatus;

import javax.persistence.*;
import java.util.Date;

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
    private Date dateAndTime;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne
    @JoinColumn(name = "PROC_OR_MED_ID",nullable = false)
    private ProcedureAndMedicinePO eventType;
}

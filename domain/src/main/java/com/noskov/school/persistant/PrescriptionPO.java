package com.noskov.school.persistant;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRESCRIPTIONS")
public class PrescriptionPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    private PatientPO patient;

    @ManyToOne
    @JoinColumn(name = "PROC_OR_MED_ID", nullable = false)
    private ProcedureAndMedicinePO prescriptionType;

    @Column(name = "TIME_PATTERN",nullable = false)
    private String timePattern;

    @Column(name = "PERIOD", nullable = false)
    private Date period;

    @Column(name = "DOSE", nullable = true)
    private float dose;
}

package com.noskov.school.persistent;

import javax.persistence.*;

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

    @Column(name = "FORMED_PRESCRIPTION",nullable = false)
    private String formedPrescription;
}

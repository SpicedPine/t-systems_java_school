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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientPO getPatient() {
        return patient;
    }

    public void setPatient(PatientPO patient) {
        this.patient = patient;
    }

    public ProcedureAndMedicinePO getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(ProcedureAndMedicinePO prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    public String getFormedPrescription() {
        return formedPrescription;
    }

    public void setFormedPrescription(String formedPrescription) {
        this.formedPrescription = formedPrescription;
    }

    public PrescriptionPO(PatientPO patient, ProcedureAndMedicinePO prescriptionType, String formedPrescription) {
        this.patient = patient;
        this.prescriptionType = prescriptionType;
        this.formedPrescription = formedPrescription;
    }

    public PrescriptionPO() {
    }
}

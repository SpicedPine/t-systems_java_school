package com.noskov.school.persistent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prescriptions")
public class PrescriptionPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientPO patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proc_or_med_id", nullable = false)
    private ProcedureAndMedicinePO prescriptionType;

    @Column(name = "formed_prescription",nullable = false)
    private String formedPrescription;

    /*@OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<EventPO> eventList = new ArrayList<>();*/

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

    public PrescriptionPO(Long id, PatientPO patient, ProcedureAndMedicinePO prescriptionType, String formedPrescription) {
        this.id = id;
        this.patient = patient;
        this.prescriptionType = prescriptionType;
        this.formedPrescription = formedPrescription;
    }

    public PrescriptionPO() {
    }
}

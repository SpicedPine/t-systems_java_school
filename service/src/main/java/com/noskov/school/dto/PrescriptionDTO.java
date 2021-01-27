package com.noskov.school.dto;

import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.service.imp.prescription.PrescriptionScratch;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionDTO {
    private PrescriptionScratch scratch;
    private PatientPO patient;
    private ProcedureAndMedicinePO procOrMedicine;

    public PrescriptionDTO() {
        this.scratch = new PrescriptionScratch();
    }

    public PrescriptionDTO(PatientPO patient) {
        this.patient = patient;
    }

    public PrescriptionDTO(PrescriptionScratch scratch, PatientPO patient, ProcedureAndMedicinePO procOrMedicine) {
        this.scratch = scratch;
        this.patient = patient;
        this.procOrMedicine = procOrMedicine;
    }

    public PrescriptionScratch getScratch() {
        return scratch;
    }

    public void setScratch(PrescriptionScratch scratch) {
        this.scratch = scratch;
    }

    public PatientPO getPatient() {
        return patient;
    }

    public void setPatient(PatientPO patient) {
        this.patient = patient;
    }

    public ProcedureAndMedicinePO getProcOrMedicine() {
        return procOrMedicine;
    }

    public void setProcOrMedicine(ProcedureAndMedicinePO procOrMedicine) {
        this.procOrMedicine = procOrMedicine;
    }
}

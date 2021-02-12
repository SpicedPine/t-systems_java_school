package com.noskov.school.dto;

import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;

import java.util.List;

public class PatientProfileDTO {
    private PatientPO patientPO;
    private List<PrescriptionPO> prescriptionPOList;

    public PatientProfileDTO(PatientPO patientPO, List<PrescriptionPO> prescriptionPOList) {
        this.patientPO = patientPO;
        this.prescriptionPOList = prescriptionPOList;
    }

    public List<PrescriptionPO> getPrescriptionPOList() {
        return prescriptionPOList;
    }

    public void setPrescriptionPOList(List<PrescriptionPO> prescriptionPOList) {
        this.prescriptionPOList = prescriptionPOList;
    }

    public PatientPO getPatientPO() {
        return patientPO;
    }

    public void setPatientPO(PatientPO patientPO) {
        this.patientPO = patientPO;
    }
}

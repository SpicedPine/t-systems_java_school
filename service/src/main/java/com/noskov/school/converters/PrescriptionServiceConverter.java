package com.noskov.school.converters;

import com.noskov.school.dto.PrescriptionDTO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.dto.prescription.PrescriptionBuilder;
import com.noskov.school.dto.prescription.PrescriptionScratch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionServiceConverter {

    @Autowired
    PrescriptionBuilder prescriptionBuilder;

    public PrescriptionPO convertToPO(PrescriptionDTO prescriptionDTO){
        PatientPO patientPO = prescriptionDTO.getPatient();
        ProcedureAndMedicinePO procOrMed = prescriptionDTO.getProcOrMedicine();
        String formedPrescription = prescriptionBuilder.buildPrescription(prescriptionDTO.getScratch());

        return new PrescriptionPO(patientPO,procOrMed,formedPrescription);
    }

    public PrescriptionDTO convertToDTO(PrescriptionPO prescriptionPO){
        PatientPO patient = prescriptionPO.getPatient();
        ProcedureAndMedicinePO procOrMed = prescriptionPO.getPrescriptionType();
        String formedPrescription = prescriptionPO.getFormedPrescription();
        PrescriptionScratch scratch = prescriptionBuilder.parsePrescription(formedPrescription);

        return new PrescriptionDTO(scratch, patient, procOrMed);
    }

}

package com.noskov.school.dto.prescription.api.time;

import com.noskov.school.dto.prescription.PrescriptionScratch;

public interface TimeManagement {
    String buildPrescription(PrescriptionScratch scratch);

    PrescriptionScratch parsePrescription(String prescription) throws Exception;
}

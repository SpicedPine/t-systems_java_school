package com.noskov.school.service.api.time;

import com.noskov.school.service.imp.prescription.PrescriptionScratch;

public interface TimeManagement {
    String buildPrescription(PrescriptionScratch scratch);

    PrescriptionScratch parsePrescription(String prescription) throws Exception;
}

package com.noskov.school.utils;

import com.noskov.school.utils.prescription.PrescriptionScratch;

public interface TimeManagement {
    String buildPrescription(PrescriptionScratch scratch);

    PrescriptionScratch parsePrescription(String prescription);
}

package com.noskov.school.utils.prescription;

import com.noskov.school.utils.TherapyType;
import com.noskov.school.utils.TimeManagement;
import java.util.StringJoiner;

public class PrescriptionBuilder implements TimeManagement {
    @Override
    public String buildPrescription(PrescriptionScratch scratch) {
        StringJoiner stringPrescription = new StringJoiner(" ");
        String therapyType = scratch.getType().toString();
        String timePattern = scratch.getTimePattern().toString();
        String period = scratch.getPeriod().toString();

        stringPrescription.add(therapyType)
                .add(timePattern)
                .add(period);

        if(scratch.getType().getTherapyType().equals(TherapyType.MEDICINE)){
            stringPrescription.add(scratch.getDose().toString());
        }

        return stringPrescription.toString();
    }
}

package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.TherapyType;
import com.noskov.school.enums.TimePeriods;
import com.noskov.school.service.api.time.TimeManagement;

import java.util.Arrays;
import java.util.List;
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

    @Override
    public PrescriptionScratch parsePrescription(String prescription) {
        PrescriptionScratch scratch = null;
        TherapyType therapyType = extractType(prescription);
        if(therapyType == TherapyType.PROCEDURE){
            scratch = ProcedureParser.parseProcedure(prescription);
        } else if (therapyType == TherapyType.MEDICINE){
            scratch = MedicineParser.parseMedicine(prescription);
        }
        if (scratch != null){
            return scratch;
        } else throw new RuntimeException("prescription parsing exception");
    }

    private static TherapyType extractType(String prescription) {
        List<String> list = getPrescriptionStringList(prescription);
        String type = list.get(0);
        if (type.equals(TherapyType.PROCEDURE.toString())){
            return TherapyType.PROCEDURE;
        } else if (type.equals(TherapyType.MEDICINE.toString())){
            return TherapyType.MEDICINE;
        } else throw new RuntimeException("type parsing Exception");
    }

    public static List<String> getPrescriptionStringList(String prescrition){
        return Arrays.asList(prescrition.split(" "));
    }
}

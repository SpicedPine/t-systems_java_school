package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.TherapyType;

import java.util.Arrays;
import java.util.List;

import static com.noskov.school.service.imp.prescription.PeriodParser.parsePeriod;
import static com.noskov.school.service.imp.prescription.TimePatternParser.parseTimePattern;

public class MedicineParser {
    public static PrescriptionScratch parseMedicine(String prescription){
        PrescriptionScratch scratch = new PrescriptionScratch();
        scratch.getType().setTherapyType(TherapyType.MEDICINE);
        scratch.setTimePattern(parseTimePattern(prescription, scratch));
        scratch.setPeriod(parsePeriod(prescription, scratch));
        scratch.getDose().setDose(parseDose(prescription));

        return scratch;
    }

    private static String parseDose(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        return list.get(list.size()-1);
    }
}

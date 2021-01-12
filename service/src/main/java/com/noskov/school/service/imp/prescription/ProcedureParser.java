package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.TherapyType;


import static com.noskov.school.service.imp.prescription.TimePatternParser.parseTimePattern;
import static com.noskov.school.service.imp.prescription.PeriodParser.parsePeriod;

public class ProcedureParser {
    public static PrescriptionScratch parseProcedure(String prescription){
        PrescriptionScratch scratch = new PrescriptionScratch();
        scratch.getType().setTherapyType(TherapyType.PROCEDURE);
        scratch.getType().setTherapyName(TypeParser.parseTherapyTypeName(prescription));
        scratch.setTimePattern(parseTimePattern(prescription, scratch));
        scratch.setPeriod(parsePeriod(prescription, scratch));
        scratch.setDose(null);
        return scratch;
    }
}

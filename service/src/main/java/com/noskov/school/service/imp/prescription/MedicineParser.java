package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.TherapyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.noskov.school.service.imp.prescription.PeriodParser.parsePeriod;
import static com.noskov.school.service.imp.prescription.TimePatternParser.parseTimePattern;

public class MedicineParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicineParser.class);

    public static PrescriptionScratch parseMedicine(String prescription){
        PrescriptionScratch scratch = new PrescriptionScratch();
        scratch.getType().setTherapyType(TherapyType.MEDICINE);
        scratch.getType().setTherapyName(TypeParser.parseTherapyTypeName(prescription));
        scratch.setTimePattern(parseTimePattern(prescription, scratch));
        scratch.setPeriod(parsePeriod(prescription, scratch));
        scratch.getDose().setDose(parseDose(prescription));

        LOGGER.info("Medicine scratch was build for prescription: {}", prescription);
        return scratch;
    }

    private static String parseDose(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        LOGGER.info("Medicine's dose: {}", list.get(list.size()-1));
        return list.get(list.size()-1);
    }
}

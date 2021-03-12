package com.noskov.school.dto.prescription;

import com.noskov.school.enums.TherapyType;
import com.noskov.school.dto.prescription.api.time.TimeManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Service
public class PrescriptionBuilder implements TimeManagement {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionBuilder.class);

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

        LOGGER.info("Builded prescription: {}", stringPrescription.toString());
        return stringPrescription.toString();
    }

    @Override
    public PrescriptionScratch parsePrescription(String prescription) {
        PrescriptionScratch scratch = null;
        TherapyType therapyType = extractType(prescription);
        if(therapyType == TherapyType.PROCEDURE){
            LOGGER.info("Started to parse procedure...");
            scratch = ProcedureParser.parseProcedure(prescription);
        } else if (therapyType == TherapyType.MEDICINE){
            LOGGER.info("Started to parse medicine...");
            scratch = MedicineParser.parseMedicine(prescription);
        }
        if (scratch != null){
            LOGGER.info("Prescription parsed");
            return scratch;
        } else {
            LOGGER.error("Couldn't parse prescription: {}", prescription);
            throw new RuntimeException("prescription parsing exception");
        }
    }

    private static TherapyType extractType(String prescription) {
        List<String> list = getPrescriptionStringList(prescription);
        String type = list.get(0);
        if (type.equals(TherapyType.PROCEDURE.toString())){
            LOGGER.info("Prescription type = procedure");
            return TherapyType.PROCEDURE;
        } else if (type.equals(TherapyType.MEDICINE.toString())){
            LOGGER.info("Prescription type = medicine");
            return TherapyType.MEDICINE;
        } else {
            LOGGER.error("Couldn't parse therapy type: {}", type);
            throw new RuntimeException("type parsing Exception");
        }
    }

    private static List<String> getPrescriptionStringList(String prescrition){
        return Arrays.asList(prescrition.split(" "));
    }
}

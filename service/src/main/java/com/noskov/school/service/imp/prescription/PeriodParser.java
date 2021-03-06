package com.noskov.school.service.imp.prescription;

public class PeriodParser {
    public static PrescriptionScratch.Period parsePeriod(String prescription, PrescriptionScratch scratch){
        scratch.getPeriod().setQuantity(QuantityParser.parsePeriodQuantity(prescription));
        scratch.getPeriod().setTimePeriod(TimePeriodParser.parsePeriodTimePeriod(prescription));

        return scratch.getPeriod();
    }
}

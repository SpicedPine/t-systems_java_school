package com.noskov.school.service.imp.prescription;

public class TimePatternParser {

    public static PrescriptionScratch.TimePattern parseTimePattern(String prescription, PrescriptionScratch scratch){
        scratch.getTimePattern().setQuantity(QuantityParser.parseTimePatternQuantity(prescription));
        scratch.getTimePattern().setFrequency(TimePeriodParser.parseTimePatternTimePeriod(prescription));
        scratch.getTimePattern().setAdditionalInformation(AdditionalInformationParser.parseAdditionalInformation(prescription));

        return scratch.getTimePattern();
    }
}

package com.noskov.school.dto.prescription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimePatternParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimePatternParser.class);

    public static PrescriptionScratch.TimePattern parseTimePattern(String prescription, PrescriptionScratch scratch){
        scratch.getTimePattern().setQuantity(QuantityParser.parseTimePatternQuantity(prescription));
        scratch.getTimePattern().setFrequency(TimePeriodParser.parseTimePatternTimePeriod(prescription));
        scratch.getTimePattern().setAdditionalInformation(AdditionalInformationParser.parseAdditionalInformation(prescription));

        LOGGER.info("Parsed time pattern");
        return scratch.getTimePattern();
    }
}

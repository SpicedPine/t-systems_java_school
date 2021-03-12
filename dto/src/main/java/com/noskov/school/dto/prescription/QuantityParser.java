package com.noskov.school.dto.prescription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class QuantityParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuantityParser.class);

    public static int parseTimePatternQuantity(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        String quantity = list.get(2);
        try{
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e){
            LOGGER.error("Couldn't parse timePatternQuantity: {}", quantity);
            throw new RuntimeException("timePatternQuantity parsing exception");
        }
    }

    public static int parsePeriodQuantity(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        int quantity;
        for (int i = 4; i < list.size()-1; i++) {
            try{
                quantity = Integer.parseInt(list.get(i));
                return quantity;
            } catch (NumberFormatException e){
                LOGGER.debug("Couldn't parse as period quantity: {}", list.get(i));
            }
        }
        LOGGER.error("Couldn't parse periodQuantity");
        throw new RuntimeException("periodQuantity parsing exception");
    }
}

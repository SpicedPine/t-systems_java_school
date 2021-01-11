package com.noskov.school.service.imp.prescription;

import java.util.Arrays;
import java.util.List;

public class QuantityParser {
    public static int parseTimePatternQuantity(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        String quantity = list.get(1);
        try{
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e){
            throw new RuntimeException("timePatternQuantity parsing exception");
        }
    }

    public static int parsePeriodQuantity(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        int quantity;
        for (int i = 3; i < list.size()-2; i++) {
            try{
                quantity = Integer.parseInt(list.get(i));
                return quantity;
            } catch (NumberFormatException e){
            }
        }
        throw new RuntimeException("periodQuantity parsinf exception");
    }
}

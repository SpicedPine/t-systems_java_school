package com.noskov.school.service.imp.prescription;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class AdditionalInformationParser {
    public static String parseAdditionalInformation(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        int lastIndex = -1;
        for (int i = 3; i < list.size()-1; i++) {
            try{
                Integer.parseInt(list.get(i));
                lastIndex = i;
                break;
            } catch (NumberFormatException e){
            }
        }
        if (lastIndex == -1){
            throw new RuntimeException("additionalInformation parsing exception");
        } else {
            StringJoiner additionalInformation = new StringJoiner(" "," ","");
            list.stream().limit(lastIndex).forEach(additionalInformation::add);
            return additionalInformation.toString();
        }
    }
}

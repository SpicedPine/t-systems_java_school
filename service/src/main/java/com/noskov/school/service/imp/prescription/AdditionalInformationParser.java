package com.noskov.school.service.imp.prescription;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class AdditionalInformationParser {
    public static String parseAdditionalInformation(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        int lastIndex = -1;
        for (int i = 4; i < list.size()-1; i++) {
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

    public static List<LocalTime> parseDayTime(String additionalInformation){
        ArrayList<LocalTime> list = new ArrayList<>();
        String morningPattern = ".* morning.*";
        String eveningPattern = ".* evening.*";
        String beforeMealPattern = ".* before meal.*";
        String afterMealPattern = ".* after meal.*";
        String morningPlusEveningPattern = ".* morning and evening.*";

        if(additionalInformation.contains(morningPlusEveningPattern)
                && additionalInformation.contains(beforeMealPattern)){
            list.add(LocalTime.parse("07:45:00"));
            list.add(LocalTime.parse("17:45:00"));
            return list;
        } else if(additionalInformation.contains(morningPlusEveningPattern)
                && additionalInformation.contains(afterMealPattern)){
            list.add(LocalTime.parse("8:40:00"));
            list.add(LocalTime.parse("18:40:00"));
            return list;
        } else if(additionalInformation.contains(morningPlusEveningPattern)){
            list.add(LocalTime.parse("9:00:00"));
            list.add(LocalTime.parse("20:00:00"));
            return list;
        } else if(additionalInformation.contains(morningPattern)
                && additionalInformation.contains(beforeMealPattern)){
            list.add(LocalTime.parse("07:45:00"));
            return list;
        } else if(additionalInformation.contains(morningPattern)
                && additionalInformation.contains(afterMealPattern)){
            list.add(LocalTime.parse("08:40:00"));
            return list;
        } else if(additionalInformation.contains(eveningPattern)
                && additionalInformation.contains(beforeMealPattern)){
            list.add(LocalTime.parse("17:45:00"));
            return list;
        } else if(additionalInformation.contains(eveningPattern)
                && additionalInformation.contains(afterMealPattern)){
            list.add(LocalTime.parse("18:40:00"));
            return list;
        } else if(additionalInformation.contains(morningPattern)){
            list.add(LocalTime.parse("9:00:00"));
            return list;
        } else if(additionalInformation.contains(eveningPattern)){
            list.add(LocalTime.parse("20:00:00"));
        }
        throw new RuntimeException("dayTime parsing exception");
    }
}

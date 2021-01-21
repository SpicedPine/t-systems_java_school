package com.noskov.school.service.imp.prescription;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class AdditionalInformationParser {
    public static String parseAdditionalInformation(String prescription) {
        List<String> list = Arrays.asList(prescription.split(" "));
        int lastIndex = -1;
        for (int i = 4; i < list.size() - 1; i++) {
            try {
                Integer.parseInt(list.get(i));
                lastIndex = i;
                break;
            } catch (NumberFormatException e) {
            }
        }
        if (lastIndex == -1) {
            throw new RuntimeException("additionalInformation parsing exception");
        } else {
            StringJoiner additionalInformation = new StringJoiner(" ", " ", "");
            list.stream().limit(lastIndex).forEach(additionalInformation::add);
            return additionalInformation.toString();
        }
    }


    public static List<LocalTime> parseDayTime(String additionalInformation) {
        ArrayList<LocalTime> list = new ArrayList<>();
        String morningPattern = ".* morning.*";
        String eveningPattern = ".* evening.*";
        String beforeMealPattern = ".* before meal.*";
        String afterMealPattern = ".* after meal.*";
        String morningPlusEveningPattern = ".* morning and evening.*";

        String MORNING_BEFORE_MEAL = "07:45:00";
        String EVENING_BEFORE_MEAL = "17:45:00";

        String MORNING_AFTER_MEAL = "8:40:00";
        String EVENING_AFTER_MEAL = "18:40:00";

        String MORNING = "9:00:00";
        String EVENING = "20:00:00";

        Map<String, List<String>> map = Map.of(
                "ME_B", List.of(MORNING_BEFORE_MEAL, EVENING_BEFORE_MEAL),
                "ME_A", List.of(MORNING_AFTER_MEAL, EVENING_AFTER_MEAL),
                "M_B", List.of(MORNING_BEFORE_MEAL),
                "M_A", List.of(MORNING_AFTER_MEAL),
                "E_B", List.of(EVENING_BEFORE_MEAL),
                "E_A", List.of(EVENING_AFTER_MEAL),
                "M", List.of(EVENING_AFTER_MEAL)
        );

        String pattern = extractPatterns(additionalInformation);

        return map.get(pattern).stream()
                .map(LocalTime::parse)
                .collect(Collectors.toList());
    }

    private static String extractPatterns(String additionalInformation) {
        String morningPattern = ".* morning.*";
        String eveningPattern = ".* evening.*";
        String beforeMealPattern = ".* before meal.*";
        String afterMealPattern = ".* after meal.*";
        String morningPlusEveningPattern = ".* morning and evening.*";

        String key = "";
        if (additionalInformation.contains(morningPlusEveningPattern)) {
            key += "ME" ;
        } else  if (additionalInformation.contains(morningPattern)) {
            key += "M" ;
        }  else if (additionalInformation.contains(eveningPattern)) {
            key += "E" ;
        }

        if (additionalInformation.contains(beforeMealPattern)) {
            key += "B";
        }

        if (additionalInformation.contains(afterMealPattern)) {
            key += "A";
        }

        return key;
    }
}

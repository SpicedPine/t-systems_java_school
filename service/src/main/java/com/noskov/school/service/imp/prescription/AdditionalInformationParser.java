package com.noskov.school.service.imp.prescription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class AdditionalInformationParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdditionalInformationParser.class);

    static String MORNING_BEFORE_MEAL = "07:45:00";
    static String EVENING_BEFORE_MEAL = "17:45:00";
    static String MORNING_AFTER_MEAL = "08:40:00";
    static String EVENING_AFTER_MEAL = "18:40:00";
    static String MORNING = "09:00:00";
    static String EVENING = "20:00:00";

    static String MORNING_PATTERN = "morning";
    static String EVENING_PATTERN = "evening";
    static String BEFORE_MEAL_PATTERN = "before meal";
    static String AFTER_MEAL_PATTERN = "after meal";
    static String MORNING_PLUS_EVENING_PATTERN = "morning and evening";
    
    static Map<String, List<String>> MAP_OF_DAY_TIMES = Map.of(
            "ME_B", List.of(MORNING_BEFORE_MEAL, EVENING_BEFORE_MEAL),
            "ME_A", List.of(MORNING_AFTER_MEAL, EVENING_AFTER_MEAL),
            "M_B", List.of(MORNING_BEFORE_MEAL),
            "M_A", List.of(MORNING_AFTER_MEAL),
            "E_B", List.of(EVENING_BEFORE_MEAL),
            "E_A", List.of(EVENING_AFTER_MEAL),
            "ME", List.of(MORNING, EVENING),
            "M", List.of(MORNING),
            "E", List.of(EVENING)
    );

    public static String parseAdditionalInformation(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        int lastIndex = -1;
        for (int i = 4; i < list.size()-1; i++) {
            try{
                Integer.parseInt(list.get(i));
                lastIndex = i;
                break;
            } catch (NumberFormatException e){
                LOGGER.debug("trying to find additional information...");
            }
        }
        if (lastIndex == -1){
            LOGGER.error("Couldn't parse additional information for prescription: {}", prescription);
            throw new RuntimeException("additionalInformation parsing exception");
        } else {
            StringJoiner additionalInformation = new StringJoiner(" "," ","");
            list.stream().skip(4).limit(lastIndex-4).forEach(additionalInformation::add);
            LOGGER.info("Parsed additional information: {}", additionalInformation.toString());
            return additionalInformation.toString();
        }
    }

    public static List<LocalTime> parseDayTime(String additionalInformation){
       String keyForPattern = extractPatterns(additionalInformation);

       if(!keyForPattern.isEmpty()){
           LOGGER.info("Key for pattern: {}", keyForPattern);
           return MAP_OF_DAY_TIMES.get(keyForPattern).stream()
                   .map(LocalTime::parse)
                   .collect(Collectors.toList());
       }
       LOGGER.info("Key for pattern was empty");
       return new ArrayList<>();
    }

    private static String extractPatterns(String additionalInformation) {
        StringJoiner key = new StringJoiner("_");
        if (additionalInformation.contains(MORNING_PLUS_EVENING_PATTERN)) {
            key.add("ME");
        } else  if (additionalInformation.contains(MORNING_PATTERN)) {
            key.add("M");
        }  else if (additionalInformation.contains(EVENING_PATTERN)) {
            key.add("E");
        }

        if (additionalInformation.contains(BEFORE_MEAL_PATTERN)) {
            key.add("B");
        }

        if (additionalInformation.contains(AFTER_MEAL_PATTERN)) {
            key.add("A");
        }

        return key.toString();
    }
}

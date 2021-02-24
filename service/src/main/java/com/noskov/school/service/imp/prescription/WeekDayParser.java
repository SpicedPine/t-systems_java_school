package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.WeekDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class WeekDayParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeekDayParser.class);

    public static Set<String> parseWeekDays(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        Set<String> weekDaySet = WeekDay.getStringSet();
        weekDaySet = weekDaySet.stream().map(String::toLowerCase).collect(Collectors.toSet());
        Set<String> containingDays = list.stream().filter(weekDaySet::contains).collect(Collectors.toSet());
        containingDays = containingDays.stream().map(String::toUpperCase).collect(Collectors.toSet());

        LOGGER.info("Parsed week days...");
        return containingDays;
    }
}

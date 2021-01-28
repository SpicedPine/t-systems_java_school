package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.WeekDay;

import java.util.*;
import java.util.stream.Collectors;

public class WeekDayParser {

    public static Set<String> parseWeekDays(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        Set<String> weekDaySet = WeekDay.getStringSet();
        weekDaySet = weekDaySet.stream().map(String::toLowerCase).collect(Collectors.toSet());
        Set<String> containingDays = list.stream().filter(weekDaySet::contains).collect(Collectors.toSet());
        containingDays = containingDays.stream().map(String::toUpperCase).collect(Collectors.toSet());
        return containingDays;
    }
}

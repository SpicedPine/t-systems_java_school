package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.WeekDay;

import java.util.*;
import java.util.stream.Collectors;

public class WeekDayParser {

    public static Set<String> parseWeekDays(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        Set<String> weekDaySet = WeekDay.getStringSet();
        Set<String> containingDays = list.stream().filter(weekDaySet::contains).collect(Collectors.toSet());
        containingDays.forEach(String::toUpperCase);
        return containingDays;
    }
}

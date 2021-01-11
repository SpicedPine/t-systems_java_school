package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.WeekDay;

import java.util.*;
import java.util.stream.Collectors;

public class WeekDayParser {

    public static String parseWeekDays(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        StringJoiner weekDayInformation = new StringJoiner(" ","on", "");
        Set<String> weekDaySet = WeekDay.getStringSet();
        List<String> containingDays = list.stream().filter(weekDaySet::contains).collect(Collectors.toList());
        containingDays.forEach(weekDayInformation::add);
        return weekDayInformation.toString();
    }
}

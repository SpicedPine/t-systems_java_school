package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.WeekDay;

import java.util.*;
import java.util.stream.Collectors;

public class WeekDayParser {

    public static List<String> parseWeekDays(PrescriptionScratch scratch, List<String> list){
        StringJoiner weekDayInformation = new StringJoiner(" ","on", "");
        Set<String> weekDaySet = WeekDay.getStringSet();
        List<String> containingDays = list.stream().filter(weekDaySet::contains).collect(Collectors.toList());
        containingDays.forEach(weekDayInformation::add);
        scratch.getTimePattern().addAdditionalInformation(weekDayInformation.toString());
        list.removeAll(containingDays);
        return list;
    }
}

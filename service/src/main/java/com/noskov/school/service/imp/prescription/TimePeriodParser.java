package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.TimePeriods;

import java.util.List;

public class TimePeriodParser {
    public static List<String> parseDay(PrescriptionScratch scratch, List<String> list){
        scratch.getTimePattern().setFrequency(TimePeriods.DAY);
        list.remove(0);
        return list;
    }

    public static List<String> parseWeek(PrescriptionScratch scratch, List<String> list){
        scratch.getTimePattern().setFrequency(TimePeriods.WEEK);
        list.remove(0);
        return list;
    }

    public static List<String> parseMonth(PrescriptionScratch scratch, List<String> list){
        scratch.getTimePattern().setFrequency(TimePeriods.Month);
        list.remove(0);
        return list;
    }
}

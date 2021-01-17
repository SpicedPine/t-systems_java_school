package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.TimePeriods;

import java.util.Arrays;
import java.util.List;

public class TimePeriodParser {

    public static TimePeriods parseTimePatternTimePeriod(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        String timePeriod = list.get(3);
        if(timePeriod.equals(TimePeriods.DAY.toString())){
            return TimePeriods.DAY;
        } else if (timePeriod.equals(TimePeriods.WEEK.toString())){
            return TimePeriods.WEEK;
        } else if (timePeriod.equals(TimePeriods.Month.toString())){
            return TimePeriods.Month;
        } else throw new RuntimeException("timePatternTimePeriod parsing exception");
    }

    public static TimePeriods parsePeriodTimePeriod(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        String timePeriod = "";
        for (int i = 4; i < list.size()-1; i++) {
            if(timePeriod.equals(TimePeriods.DAY.toString())){
                return TimePeriods.DAY;
            } else if (timePeriod.equals(TimePeriods.WEEK.toString())){
                return TimePeriods.WEEK;
            } else if (timePeriod.equals(TimePeriods.Month.toString())){
                return TimePeriods.Month;
            }
        }
        throw new RuntimeException("periodTimePeriod parsing exception");
    }
}

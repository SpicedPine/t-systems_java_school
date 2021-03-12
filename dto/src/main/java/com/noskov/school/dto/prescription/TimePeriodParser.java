package com.noskov.school.dto.prescription;

import com.noskov.school.enums.TimePeriods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class TimePeriodParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimePeriodParser.class);

    public static TimePeriods parseTimePatternTimePeriod(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        String timePeriod = list.get(3);
        if(timePeriod.equals(TimePeriods.DAY.toString())){
            LOGGER.info("RimePatternTimePeriod = DAY");
            return TimePeriods.DAY;
        } else if (timePeriod.equals(TimePeriods.WEEK.toString())){
            LOGGER.info("RimePatternTimePeriod = WEEK");
            return TimePeriods.WEEK;
        } else if (timePeriod.equals(TimePeriods.Month.toString())){
            LOGGER.info("RimePatternTimePeriod = MOUNTH");
            return TimePeriods.Month;
        } else {
            LOGGER.error("Couldn't parse timePatternTimePeriod: {}", timePeriod);
            throw new RuntimeException("timePatternTimePeriod parsing exception");
        }
    }

    public static TimePeriods parsePeriodTimePeriod(String prescription){
        List<String> list = Arrays.asList(prescription.split(" "));
        for (int i = 4; i < list.size(); i++) {
            if(list.get(i).equals(TimePeriods.DAY.toString())){
                LOGGER.info("periodTimePeriod = DAY");
                return TimePeriods.DAY;
            } else if (list.get(i).equals(TimePeriods.WEEK.toString())){
                LOGGER.info("periodTimePeriod = WEEK");
                return TimePeriods.WEEK;
            } else if (list.get(i).equals(TimePeriods.Month.toString())){
                LOGGER.info("periodTimePeriod = DAY");
                return TimePeriods.Month;
            }
        }
        LOGGER.info("Couldn't parse periodTimePeriod");
        throw new RuntimeException("periodTimePeriod parsing exception");
    }
}

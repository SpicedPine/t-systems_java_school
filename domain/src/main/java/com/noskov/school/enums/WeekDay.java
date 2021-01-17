package com.noskov.school.enums;

import java.util.EnumSet;
import java.util.HashSet;

public enum WeekDay {
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday"),
    SUNDAY("sunday");

    private String weekDay;

    WeekDay(String weekDay) {
        this.weekDay=weekDay;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public static HashSet<String> getStringSet(){
        HashSet<String> set = new HashSet<>();
        for (WeekDay day : getAllDays()) {
            set.add(day.toString());
        }
        return set;
    }
    public static EnumSet<WeekDay> getAllDays(){
        return EnumSet.allOf(WeekDay.class);
    }
}

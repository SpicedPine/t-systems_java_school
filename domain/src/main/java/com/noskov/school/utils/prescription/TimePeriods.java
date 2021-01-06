package com.noskov.school.utils.prescription;

public enum TimePeriods {
    DAY("day"),
    WEEK("week"),
    Month("month");

    private String timePeriod;

    TimePeriods(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    @Override
    public String toString(){
        return timePeriod;
    }
}

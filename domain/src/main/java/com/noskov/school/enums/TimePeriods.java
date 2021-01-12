package com.noskov.school.enums;

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

    public static int oneInOtherCount(TimePeriods innerPeriod,TimePeriods outerPeriod){
        switch (outerPeriod){
            case DAY:
                switch (innerPeriod){
                    case DAY:
                        return 1;
                }
            case WEEK:
                switch (innerPeriod){
                    case DAY:
                        return 7;
                    case WEEK:
                        return 1;
                }
            case Month:
                switch (innerPeriod){
                    case DAY:
                        return 30;
                    case WEEK:
                        return 4;
                    case Month:
                        return 1;
                }
        }
        throw new RuntimeException("countingPeriods exception");
    }
}

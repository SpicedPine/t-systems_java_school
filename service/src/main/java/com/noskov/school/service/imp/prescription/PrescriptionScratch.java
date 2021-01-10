package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.TherapyType;
import com.noskov.school.enums.TimePeriods;

public class PrescriptionScratch {
    private Type type;
    private TimePattern timePattern;
    private Period period;
    private Dose dose;

    public PrescriptionScratch() {
    }

    public PrescriptionScratch(Type type, TimePattern timePattern, Period period, Dose dose) {
        this.type = type;
        this.timePattern = timePattern;
        this.period = period;
        this.dose = dose;
    }

    public Type getType() {
        return type;
    }

    public TimePattern getTimePattern() {
        return timePattern;
    }

    public Period getPeriod() {
        return period;
    }

    public Dose getDose() {
        return dose;
    }

    class Type{
        private TherapyType therapyType;

        public Type(TherapyType therapyType) {
            this.therapyType = therapyType;
        }

        @Override
        public String toString(){
            return therapyType.toString();
        }

        public TherapyType getTherapyType() {
            return therapyType;
        }

        public void setTherapyType(TherapyType therapyType) {
            this.therapyType = therapyType;
        }
    }

    class TimePattern{
        private int quantity;
        private TimePeriods frequency;
        private String additionalInformation;

        public TimePattern(int quantity, TimePeriods frequency, String additionalInformation) {
            this.quantity = quantity;
            this.frequency = frequency;
            this.additionalInformation = additionalInformation;
        }

        @Override
        public String toString(){
            return quantity + " " + frequency.toString() + " " + additionalInformation;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setFrequency(TimePeriods frequency) {
            this.frequency = frequency;
        }

        public void setAdditionalInformation(String additionalInformation) {
            this.additionalInformation = additionalInformation;
        }

        public void addAdditionalInformation(String informationToAdd){
            this.additionalInformation+=" "+informationToAdd;
        }
    }

    class Period{
        private int quantity;
        private TimePeriods timePeriod;

        public Period(int quantity, TimePeriods timePeriod) {
            this.quantity = quantity;
            this.timePeriod = timePeriod;
        }

        @Override
        public String toString(){
            return quantity + " " + timePeriod.toString();
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setTimePeriod(TimePeriods timePeriod) {
            this.timePeriod = timePeriod;
        }
    }

    class Dose{
        private String dose;

        public Dose(String dose) {
            this.dose = dose;
        }

        @Override
        public String toString(){
            return dose;
        }

        public void setDose(String dose) {
            this.dose = dose;
        }
    }
}

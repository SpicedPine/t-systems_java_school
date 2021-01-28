package com.noskov.school.service.imp.prescription;

import com.noskov.school.enums.TherapyType;
import com.noskov.school.enums.TimePeriods;

public class PrescriptionScratch {
    private Type type;
    private TimePattern timePattern;
    private Period period;
    private Dose dose;

    public PrescriptionScratch() {
        this.type = new Type();
        this.timePattern = new TimePattern();
        this.period = new Period();
        this.dose = new Dose();
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

    public void setTimePattern(TimePattern timePattern) {
        this.timePattern = timePattern;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public void setDose(Dose dose) {
        this.dose = dose;
    }

    public int getTimePatternQuantity() { return timePattern.getQuantity(); }

    public TimePeriods getTimePatternTimePeriod(){ return timePattern.getFrequency(); }

    public String getTimePatternAdditionalInformation(){ return timePattern.getAdditionalInformation(); }

    public void setTimePatternQuantity(int quantity) { timePattern.setQuantity(quantity); }

    public void setTimePatternTimePeriod(TimePeriods timePeriod){ timePattern.setFrequency(timePeriod); }

    public void setTimePatternAdditionalInformation(String additionalInformation){ timePattern.setAdditionalInformation(additionalInformation); }

    public TimePeriods getPeriodTimePeriod(){ return period.getTimePeriod(); }

    public int getPeriodsQuantity(){ return period.getQuantity(); }

    public void setPeriodTimePeriod(TimePeriods timePeriod){ period.setTimePeriod(timePeriod); }

    public void setPeriodsQuantity(int quantity){ period.setQuantity(quantity); }

    public String getDoseDescription(){ return dose.getDose(); }

    public void setDoseDescription(String doseDescription){ dose.setDose(doseDescription); }

    public TherapyType getTypeTherapy(){ return type.getTherapyType();}

    public String getTypeTherapyName(){ return type.getTherapyName();}

    public void setTypeTherapy(TherapyType therapyType){ type.setTherapyType(therapyType);}

    public void setTypeTherapyName(String therapyName){ type.setTherapyName(therapyName);}

    class Type{
        private TherapyType therapyType;

        private String therapyName;

        public Type(TherapyType therapyType, String therapyName) {
            this.therapyType = therapyType;
            this.therapyName = therapyName;
        }

        public Type() {

        }

        @Override
        public String toString(){
            return therapyType.toString() + " " + therapyName;
        }

        public String getTherapyName() {
            return therapyName;
        }

        public void setTherapyName(String therapyName) {
            this.therapyName = therapyName;
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

        public TimePattern() {

        }

        @Override
        public String toString(){
            if(additionalInformation==null || additionalInformation.isEmpty()){
                return quantity + " " + frequency.toString();
            }else{
                return quantity + " " + frequency.toString() + " " + additionalInformation;
            }
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

        public TimePeriods getFrequency() {
            return frequency;
        }

        public String getAdditionalInformation() {
            return additionalInformation;
        }
    }

    class Period{
        private int quantity;
        private TimePeriods timePeriod;

        public Period(int quantity, TimePeriods timePeriod) {
            this.quantity = quantity;
            this.timePeriod = timePeriod;
        }

        public Period() {

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

        public int getQuantity() {
            return quantity;
        }

        public TimePeriods getTimePeriod() {
            return timePeriod;
        }
    }

    class Dose{
        private String dose;

        public Dose(String dose) {
            this.dose = dose;
        }

        public Dose() {

        }

        @Override
        public String toString(){
            return dose;
        }

        public void setDose(String dose) {
            this.dose = dose;
        }

        public String getDose() {
            return dose;
        }
    }
}

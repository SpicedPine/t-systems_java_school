package com.noskov.school.service.imp.prescription;

public class Dose {
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

package com.noskov.school.utils;

public enum TherapyType {
    PROCEDURE("procedure"),
    MEDICINE("medicine");

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    TherapyType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}

package com.noskov.school.enums;

public enum TherapyType {
    PROCEDURE("procedure"),
    MEDICINE("medicine");

    private String type;

    public String getType() {
        return type;
    }

    TherapyType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}

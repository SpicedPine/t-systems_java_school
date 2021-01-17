package com.noskov.school.enums;

public enum StaffPost {
    PHYSITIAN("physitian"),
    NURSE("nurse");

    private String type;

    StaffPost(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString(){
        return type;
    }
}

package com.noskov.school.utils;

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

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}

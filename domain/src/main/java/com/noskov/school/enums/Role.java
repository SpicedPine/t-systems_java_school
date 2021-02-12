package com.noskov.school.enums;

import java.util.Locale;

public enum Role {
    PHYSITIAN("ROLE_PHYSICIAN"),
    NURSE("ROLE_NURSE");

    private String type;

    Role(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString(){
        return type.toLowerCase().substring(5);
    }
}

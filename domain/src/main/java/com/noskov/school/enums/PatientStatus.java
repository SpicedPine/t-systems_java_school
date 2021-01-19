package com.noskov.school.enums;

public enum PatientStatus {
    TREATING("treating"),
    TREATED("treated");

    private String status;

    PatientStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}

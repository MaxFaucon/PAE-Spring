package com.example.demo.model;

public enum Gender {
    MALE, FEMALE;

    @Override
    public String toString() {
        switch (this) {
            case MALE:
                return "M";
            case FEMALE:
                return "F";
            default:
                return "";
        }
    }
}

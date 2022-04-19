package com.debargha.model;

public enum Sex {
    MALE("Male"),
    FEMALE("Female"),
    OTHERS("Others");

    private final String displayName;

    private Sex(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

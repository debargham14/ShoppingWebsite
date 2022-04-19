package com.debargha.model;

public enum Preference {
    SEASONAL("Seasonal"),
    NEW_ARRIVAL("New Arrival");

    private final String displayName;

    private Preference(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

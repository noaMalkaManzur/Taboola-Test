package org.example.dataManagement.api;

public enum MetricEnum {
    COUNTRY(0),
    BROWSER(1),
    OS(2);

    private final int index;

    MetricEnum(int index) {
        this.index = index;
    }
    public int getValue() {
        return index;
    }
}

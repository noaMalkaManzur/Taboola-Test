package org.example.logic.api;

public interface CollectAndAnalyzeData {

    public void addValueToData(String value);
    public double getPercentPerValue(String value);
    public void printAnalyzeData(String metricName);
    public void sortDescending();
}

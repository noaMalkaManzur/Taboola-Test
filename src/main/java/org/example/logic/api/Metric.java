package org.example.logic.api;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Metric implements CollectAndAnalyzeData {

    protected int totalAmount = 0;
    protected Map<String, Integer> dataPair = new HashMap<String, Integer>();

    @Override
    public void addValueToData(String value) {

        totalAmount++;
        int counter = dataPair.getOrDefault(value, 1) + 1;
        dataPair.put(value, counter);

    }

    @Override
    public double getPercentPerValue(String value) throws IllegalArgumentException,ArithmeticException {
        int counter = dataPair.get(value);

        if(counter ==0){
            throw new IllegalArgumentException("The specified value does not exist in the data.");

        }
        if(totalAmount==0){
            throw new ArithmeticException("Can not divide by zero.");
        }

        return (double) (dataPair.get(value) * 100) / totalAmount;

    }
    @Override
    public void printAnalyzeData(String metricName){

        System.out.println(metricName + ":");
        sortDescending();
        for (Map.Entry<String, Integer> entry : dataPair.entrySet()) {
            String nameValue = entry.getKey();
            double percent = getPercentPerValue(nameValue);
            System.out.printf("%s - %.2f%%\n", nameValue, percent);
        }


    }
    @Override
    public void sortDescending(){

        dataPair = dataPair.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}

package com.techelevator;

public class Quarter implements Money{
    private String name = "Quarter";
    private double value = 0.25;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        return value;
    }
}

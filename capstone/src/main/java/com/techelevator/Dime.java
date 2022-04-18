package com.techelevator;

public class Dime implements Money{
    private String name = "Dime";
    private double value = 0.10;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        return value;
    }
}

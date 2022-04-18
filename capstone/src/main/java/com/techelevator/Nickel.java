package com.techelevator;

public class Nickel implements Money{
    private String name = "Nickel";
    private double value = 0.05;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        return value;
    }
}

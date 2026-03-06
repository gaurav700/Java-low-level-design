package com.pm.impl;

import com.pm.Taxcalculator;

public class UKTaxCalculator implements Taxcalculator {
    private double TAX_RATE = 0.1;
    @Override
    public double calculateTax(double amount) {
        return amount * TAX_RATE;
    }
    @Override
    public String getRegion(){
        return "UK";
    }
}

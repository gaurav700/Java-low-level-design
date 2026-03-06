package com.pm;

public class OrderProcessor {
    Taxcalculator taxcalculator;

    OrderProcessor(Taxcalculator taxcalculator) {
        this.taxcalculator = taxcalculator;
    }

    public void processOrder(double amount){
        double tax = taxcalculator.calculateTax(amount);
        double total = amount + tax;
        System.out.printf("%s Order - Subtotal: $%.2f, Tax: $%.2f, Total: $%.2f%n",
                taxcalculator.getRegion(), amount, tax, total);
    }
}

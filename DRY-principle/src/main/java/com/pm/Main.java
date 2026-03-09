package com.pm;

import com.pm.impl.EUTaxCalculator;
import com.pm.impl.UKTaxCalculator;
import com.pm.impl.USTaxCalculator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//Problem: You have three region-specific order processors (USOrderProcessor, EUOrderProcessor, UKOrderProcessor) that each duplicate the same tax calculation logic. Your task is to extract a TaxCalculator interface with region-specific implementations, then refactor the order processors to use it.
//<br>
//Requirements: <br>
//Create a TaxCalculator interface with a calculateTax(amount) method <br>
//Implement USTaxCalculator (10% tax), EUTaxCalculator (20% tax), and UKTaxCalculator (15% tax) <br>
//Refactor the order processors to accept a TaxCalculator instead of duplicating tax logic<br>
//Each order processor should print the subtotal, tax amount, and total<br>
public class Main {
    public static void main(String[] args) {
        OrderProcessor usProcessor = new OrderProcessor(new USTaxCalculator());
        usProcessor.processOrder(2100.0);
        OrderProcessor ukProcessor = new OrderProcessor(new UKTaxCalculator());
        ukProcessor.processOrder(2200.0);
        OrderProcessor euProcessor = new OrderProcessor(new EUTaxCalculator());
        euProcessor.processOrder(2300.0);
    }
}
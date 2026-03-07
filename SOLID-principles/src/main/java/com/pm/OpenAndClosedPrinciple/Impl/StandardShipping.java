package com.pm.OpenAndClosedPrinciple.Impl;

import com.pm.OpenAndClosedPrinciple.ShippingStrategy;

public class StandardShipping implements ShippingStrategy {
    @Override
    public double calculateCost(double weight) {
        return weight*1.5;
    }
}

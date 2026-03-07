package com.pm.OpenAndClosedPrinciple.Impl;

import com.pm.OpenAndClosedPrinciple.ShippingStrategy;

public class ExpressShipping implements ShippingStrategy {
    @Override
    public double calculateCost(double weight) {
        return weight*3.0;
    }
}

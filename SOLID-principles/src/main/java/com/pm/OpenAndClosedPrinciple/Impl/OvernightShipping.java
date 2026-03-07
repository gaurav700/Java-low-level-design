package com.pm.OpenAndClosedPrinciple.Impl;

import com.pm.OpenAndClosedPrinciple.ShippingStrategy;

public class OvernightShipping implements ShippingStrategy {
    @Override
    public double calculateCost(double weight) {
        return weight*5.0;
    }
}

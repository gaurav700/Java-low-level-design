package com.pm.OpenAndClosedPrinciple.Impl;

import com.pm.OpenAndClosedPrinciple.ShippingStrategy;

public class InternationalShipping implements ShippingStrategy {
    @Override
    public double calculateCost(double weight) {
        return weight*10.0;
    }
}

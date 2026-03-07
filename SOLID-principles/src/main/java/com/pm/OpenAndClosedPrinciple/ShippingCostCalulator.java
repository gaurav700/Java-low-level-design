package com.pm.OpenAndClosedPrinciple;

public class ShippingCostCalulator {
    ShippingStrategy shippingStrategy;

    public ShippingCostCalulator(ShippingStrategy shippingStrategy) {
        this.shippingStrategy = shippingStrategy;
    }

    public double calculate(double weight){
        return shippingStrategy.calculateCost(weight);
    }
}

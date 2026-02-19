package com.pm.strategy.impl;

import com.pm.entity.Ride;
import com.pm.strategy.FareStrategy;

public class SurgeFareStrategy implements FareStrategy {

    private static final double CHARGE_PER_KM = 6.0;
    private final double surgeMultiplier;

    public SurgeFareStrategy(double surgeMultiplier) {
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public double calculateFare(Ride ride) {
        return ride.getDistance() * CHARGE_PER_KM * surgeMultiplier;
    }
}

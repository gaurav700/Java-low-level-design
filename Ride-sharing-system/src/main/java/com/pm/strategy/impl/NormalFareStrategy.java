package com.pm.strategy.impl;

import com.pm.entity.Ride;
import com.pm.strategy.FareStrategy;

public class NormalFareStrategy implements FareStrategy {

    private static final double CHARGE_PER_KM = 6.0;

    @Override
    public double calculateFare(Ride ride) {
        return ride.getDistance() * CHARGE_PER_KM;
    }
}

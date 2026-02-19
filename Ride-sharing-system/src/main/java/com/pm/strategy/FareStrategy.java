package com.pm.strategy;

import com.pm.entity.Ride;

public interface FareStrategy {
    double calculateFare(Ride ride);
}

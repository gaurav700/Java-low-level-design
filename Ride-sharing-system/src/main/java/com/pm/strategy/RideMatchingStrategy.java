package com.pm.strategy;

import com.pm.entity.Driver;
import com.pm.entity.Ride;

import java.util.List;

public interface RideMatchingStrategy {
    Driver matchDriver(Ride ride, List<Driver> availableDrivers);
}

package com.pm.service;

import com.pm.entity.*;
import com.pm.enums.RideStatus;
import com.pm.strategy.FareStrategy;
import com.pm.strategy.RideMatchingStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideService {

    private Map<String, Ride> rideStore = new HashMap<>();
    private final FareStrategy fareStrategy;
    private final RideMatchingStrategy rideMatchingStrategy;

    public RideService(FareStrategy fareStrategy,
                       RideMatchingStrategy rideMatchingStrategy) {
        this.fareStrategy = fareStrategy;
        this.rideMatchingStrategy = rideMatchingStrategy;
    }

    public Ride createRide(Rider rider,
                           Location pickup,
                           Location drop) {
        Ride ride = new Ride(rider, pickup, drop);
        rideStore.put(ride.getRideId(), ride);
        return ride;
    }

    public Ride assignDriver(String rideId,
                             List<Driver> drivers) {
        Ride ride = getRide(rideId);

        Driver driver = rideMatchingStrategy.matchDriver(ride, drivers);

        ride.assignDriver(driver);
        driver.setStatus(com.pm.enums.DriverStatus.BUSY);

        return ride;
    }

    public Ride finishRide(String rideId) {
        Ride ride = getRide(rideId);
        double fare = fareStrategy.calculateFare(ride);
        ride.completeRide(fare);

        ride.getDriver().setStatus(com.pm.enums.DriverStatus.ONLINE);

        return ride;
    }

    public Ride getRide(String rideId) {
        Ride ride = rideStore.get(rideId);
        if (ride == null) {
            throw new IllegalArgumentException("Ride not found");
        }
        return ride;
    }
}

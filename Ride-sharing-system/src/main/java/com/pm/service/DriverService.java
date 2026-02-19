package com.pm.service;

import com.pm.entity.Ride;

public class DriverService {

    private final RideService rideService;

    public DriverService(RideService rideService) {
        this.rideService = rideService;
    }

    public Ride startRide(String rideId, String otp) {
        Ride ride = rideService.getRide(rideId);
        ride.startRide(otp);
        return ride;
    }

    public Ride finishRide(String rideId) {
        return rideService.finishRide(rideId);
    }
}

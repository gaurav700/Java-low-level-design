package com.pm.service;

import com.pm.entity.Location;
import com.pm.entity.Ride;
import com.pm.entity.Rider;

public class RiderService {

    private final RideService rideService;

    public RiderService(RideService rideService) {
        this.rideService = rideService;
    }

    public Ride requestRide(Rider rider,
                            Location pickup,
                            Location drop) {

        return rideService.createRide(rider, pickup, drop);
    }

    public void cancelRide(String rideId) {
        Ride ride = rideService.getRide(rideId);
        ride.cancel();
    }

    public void rateDriver(String rideId,
                           double rating) {

        Ride ride = rideService.getRide(rideId);

        if (ride.getStatus()
                != com.pm.enums.RideStatus.COMPLETED) {
            throw new IllegalStateException(
                    "Ride not completed"
            );
        }

        ride.getDriver().setRating(rating);
    }
}

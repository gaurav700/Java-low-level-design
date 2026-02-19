package com.pm.strategy.impl;

import com.pm.entity.Driver;
import com.pm.entity.Location;
import com.pm.entity.Ride;
import com.pm.enums.DriverStatus;
import com.pm.strategy.RideMatchingStrategy;

import java.util.List;

public class NearestDriverRideMatchingStrategy
        implements RideMatchingStrategy {

    @Override
    public Driver matchDriver(Ride ride,
                              List<Driver> drivers) {

        Location pickup = ride.getPickupLocation();

        Driver nearestDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : drivers) {

            if (driver.getStatus() != DriverStatus.ONLINE) {
                continue;
            }

            Location driverLocation = driver.getCurrentLocation();

            double latDiff =
                    pickup.getLatitude() - driverLocation.getLatitude();
            double lonDiff =
                    pickup.getLongitude() - driverLocation.getLongitude();

            double distance = latDiff * latDiff + lonDiff * lonDiff;

            if (distance < minDistance) {
                minDistance = distance;
                nearestDriver = driver;
            }
        }

        return nearestDriver;
    }
}

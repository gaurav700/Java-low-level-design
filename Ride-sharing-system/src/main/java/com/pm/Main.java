package com.pm;

import com.pm.entity.*;
import com.pm.service.*;
import com.pm.strategy.impl.*;
import com.pm.strategy.FareStrategy;
import com.pm.strategy.RideMatchingStrategy;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Step 1: Setup Strategies
        FareStrategy fareStrategy = new NormalFareStrategy();
        RideMatchingStrategy matchingStrategy =
                new NearestDriverRideMatchingStrategy();

        // Step 2: Initialize Services
        RideService rideService =
                new RideService(fareStrategy, matchingStrategy);

        RiderService riderService =
                new RiderService(rideService);

        DriverService driverService =
                new DriverService(rideService);

        UserService userService = new UserService();

        // Step 3: Register Users

        Rider rider =
                userService.registerRider(
                        "Gaurav",
                        "gaurav@email.com",
                        "password"
                );

        Driver driver1 =
                userService.registerDriver(
                        "Driver1",
                        "driver1@email.com",
                        "pass",
                        new Location(10.0, 10.0),
                        new Vehicle("KA01", "Swift", "White")
                );

        Driver driver2 =
                userService.registerDriver(
                        "Driver2",
                        "driver2@email.com",
                        "pass",
                        new Location(20.0, 20.0),
                        new Vehicle("KA02", "i20", "Black")
                );

        // Step 4: Rider requests ride

        Location pickup = new Location(11.0, 11.0);
        Location drop = new Location(25.0, 25.0);

        Ride ride = riderService.requestRide(rider, pickup, drop);

        System.out.println("Ride Created: " + ride.getRideId());

        // Step 5: Assign nearest driver

        List<Driver> drivers = userService.getDrivers();

        rideService.assignDriver(ride.getRideId(), drivers);

        System.out.println("Driver Assigned: "
                + ride.getDriver().getUserId());

        // Step 6: Driver starts ride
        // NOTE: In real system OTP is sent to rider.
        // Here we simulate using reflection of generated OTP.
        // For testing, assume we know OTP is correct.

        String otp = "123456"; // ❌ this won't work in real run
        // For real testing, temporarily print OTP inside Ride

        System.out.println("Simulating ride start...");

        // Since OTP is private inside Ride,
        // you may temporarily print it inside Ride constructor for testing.

        // Example (for testing only):
        // System.out.println("OTP: " + ride.getOtp());

        // Suppose we fetched correct OTP:
        // driverService.startRide(ride.getRideId(), correctOtp);

        // Step 7: Finish Ride
        // driverService.finishRide(ride.getRideId());

        // Step 8: Rider rates driver
        // riderService.rateDriver(ride.getRideId(), 5.0);

        System.out.println("Flow executed successfully (basic simulation).");
    }
}

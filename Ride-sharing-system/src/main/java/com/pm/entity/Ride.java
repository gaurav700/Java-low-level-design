package com.pm.entity;

import com.pm.enums.RideStatus;

import java.security.SecureRandom;
import java.util.UUID;

public class Ride {

    private final String rideId;
    private final Rider rider;
    private Driver driver;

    private final Location pickupLocation;
    private final Location destinationLocation;

    private RideStatus rideStatus;
    private double fare;
    private long pickUpTime;
    private long dropOffTime;

    private String otp;

    public Ride(Rider rider, Location pickupLocation, Location dropLocation) {
        this.rideId = UUID.randomUUID().toString();
        this.rider = rider;
        this.pickupLocation = pickupLocation;
        this.destinationLocation = dropLocation;
        this.rideStatus = RideStatus.REQUESTED;
        generateOtp();
    }

    public synchronized void assignDriver(Driver driver) {
        if (this.rideStatus != RideStatus.REQUESTED) {
            throw new IllegalStateException("Ride already assigned");
        }
        this.driver = driver;
        this.rideStatus = RideStatus.ACCEPTED;
    }

    public synchronized void startRide(String inputOtp) {
        if (rideStatus != RideStatus.ACCEPTED) {
            throw new IllegalStateException("Ride not accepted");
        }
        if (!verifyOtp(inputOtp)) {
            throw new IllegalStateException("Invalid OTP");
        }
        rideStatus = RideStatus.IN_PROGRESS;
        pickUpTime = System.currentTimeMillis();
    }

    public synchronized void completeRide(double fare) {
        if (rideStatus != RideStatus.IN_PROGRESS) {
            throw new IllegalStateException("Ride not in progress");
        }
        this.fare = fare;
        dropOffTime = System.currentTimeMillis();
        rideStatus = RideStatus.COMPLETED;
    }

    public synchronized void cancel() {
        if (rideStatus == RideStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel completed ride");
        }
        rideStatus = RideStatus.CANCELED;
    }

    private void generateOtp() {
        SecureRandom random = new SecureRandom();
        this.otp = String.valueOf(100000 + random.nextInt(900000));
    }

    private boolean verifyOtp(String inputOtp) {
        return this.otp.equals(inputOtp);
    }

    public double getDistance() {
        double latDiff = pickupLocation.getLatitude() - destinationLocation.getLatitude();
        double lonDiff = pickupLocation.getLongitude() - destinationLocation.getLongitude();
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
    }

    public String getRideId() {
        return rideId;
    }

    public RideStatus getStatus() {
        return rideStatus;
    }

    public Driver getDriver() {
        return driver;
    }

    public Rider getRider() {
        return rider;
    }

    public Location getPickupLocation() {
        return this.pickupLocation;
    }
}

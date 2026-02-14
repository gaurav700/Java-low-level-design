package com.pm.entity;

import com.pm.enums.VehicleType;

public class ParkingSpot {

    private final String spotNumber;
    private final VehicleType spotType;
    private final Floor floor;

    private Vehicle vehicle;

    public ParkingSpot(String spotNumber, VehicleType spotType, Floor floor) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.floor = floor;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public void parkVehicle(Vehicle vehicle) {
        if (!isAvailable()) {
            throw new IllegalStateException("Spot already occupied");
        }
        this.vehicle = vehicle;
    }

    public void removeVehicle() {
        this.vehicle = null;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public Floor getFloor() {
        return floor;
    }
}

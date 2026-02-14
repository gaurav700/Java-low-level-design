package com.pm.entity;

import com.pm.enums.VehicleType;

public class Vehicle {

    private final VehicleType vehicleType;
    private final String registrationNumber;

    public Vehicle(VehicleType vehicleType, String registrationNumber) {
        this.vehicleType = vehicleType;
        this.registrationNumber = registrationNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}

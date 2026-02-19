package com.pm.entity;

import com.pm.enums.DriverStatus;
import com.pm.enums.Role;

public class Driver extends User {

    private Role role;
    private double walletMoney;
    private DriverStatus status;
    private Vehicle vehicle;
    private Location currentLocation;

    public Driver(String name, String email, String password,
                  Location currentLocation, Vehicle vehicle) {
        super(name, email, password);
        this.role = Role.DRIVER;
        this.walletMoney = 0.0;
        this.status = DriverStatus.ONLINE;
        this.vehicle = vehicle;
        this.currentLocation = currentLocation;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }
}

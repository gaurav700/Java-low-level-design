package com.pm.service;

import com.pm.entity.*;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<Rider> riders = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();

    public Rider registerRider(String name,
                               String email,
                               String password) {

        Rider rider = new Rider(name, email, password);
        riders.add(rider);
        return rider;
    }

    public Driver registerDriver(String name,
                                 String email,
                                 String password,
                                 Location location,
                                 Vehicle vehicle) {

        Driver driver =
                new Driver(name, email, password,
                        location, vehicle);

        drivers.add(driver);
        return driver;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }
}

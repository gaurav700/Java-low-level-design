package com.pm.entity;

public class Vehicle {

    private String registrationNumber;
    private String model;
    private String color;

    public Vehicle(String registrationNumber,
                   String model,
                   String color) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}

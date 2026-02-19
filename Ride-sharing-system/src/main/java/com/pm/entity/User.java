package com.pm.entity;

import java.util.UUID;

public abstract class User {

    private final String userId;
    private String name;
    private String email;
    private String password;
    private double rating;

    public User(String name, String email, String password) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
}

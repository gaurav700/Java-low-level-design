package com.pm.entity;

import java.util.UUID;

public class User {

    private final String userId;
    private String name;
    private String email;
    private String password;
    private Location location;

    public User(String name, String email, String password, Location location) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }

    public Location getLocation() {
        return location;
    }

    public String getUserName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
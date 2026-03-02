package com.pm.entity;

import java.util.List;
import java.util.UUID;

public class User {
    private final String userId;
    private final String username;


    public User(String userid, String username) {
        this.username = username;
        this.userId = userid;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }
}

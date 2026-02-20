package com.pm.services;

import com.pm.entity.*;
import java.util.*;

public class UserService {

    private final Map<String, User> users = new HashMap<>();

    public User register(String name,
                         String email,
                         String password,
                         Location location) {

        User user = new User(name, email, password, location);
        users.put(email, user);
        return user;
    }

    public User login(String email, String password) {
        User user = users.get(email);
        if (!user.getPassword().equals(password)) {
            throw new IllegalStateException("Invalid credentials");
        }
        return user;
    }
}
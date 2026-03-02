package com.pm.repository;

import com.pm.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public User getUser(String userId) {
        return users.get(userId);
    }

    public void addUser(User user) {
        if(users.containsKey(user.getUserId())){
            throw new IllegalStateException("user already exists");
        }
        users.put(user.getUserId(), user);
    }
}

package com.pm.InterfaceSegregationPrinciple.impl;

import com.pm.InterfaceSegregationPrinciple.UserCrud;
import com.pm.InterfaceSegregationPrinciple.entity.Role;
import com.pm.InterfaceSegregationPrinciple.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BasicUserService implements UserCrud {

    Map<String, User> users = new HashMap<>();
    @Override
    public String CreateUser(String name, String email, Role role) {
        User user = new User(name, email, role);
        users.put(user.getUserId(), user);
        return getUser(user.getUserId()).toString();
    }

    @Override
    public void UpdateUser(String userId, String newEmail, Role role) {
        User user = users.get(userId);
        if(user == null) {
            throw new IllegalArgumentException("User with id " + userId + " not exists exists");
        }
        user.setEmail(newEmail);
        user.setRole(role);
    }

    @Override
    public void DeleteUser(String userId) {
        users.remove(userId);
    }

    @Override
    public User getUser(String userId) {
        if(!users.containsKey(userId)){
            throw new IllegalStateException("User with this id does not exist");
        };
        return users.get(userId);
    }
}

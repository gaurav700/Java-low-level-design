package com.pm.entity;

import com.pm.strategy.BorrowPolicy;

import java.util.UUID;

public class User {
    private final String userId;
    private final String userName;
    private final Role role;
    private final BorrowPolicy borrowPolicy;


    public User(String userName, Role role, BorrowPolicy borrowPolicy) {
        this.role = role;
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.borrowPolicy = borrowPolicy;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Role getRole() {
        return role;
    }

    public  BorrowPolicy getBorrowPolicy() {
        return borrowPolicy;
    }
}

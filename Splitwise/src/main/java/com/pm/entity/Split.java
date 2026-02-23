package com.pm.entity;

public abstract class Split {
    protected User user;
    protected double amount;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }
}

package com.pm.entity;

public class PercentageSplit extends Split {
    private double percentage;
    public PercentageSplit(User user, double percentage) {
        this.user = user;
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }
}

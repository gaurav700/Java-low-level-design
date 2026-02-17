package com.pm.strategy.impl;

import com.pm.strategy.PriceStrategy;

public class HourlyPricingStrategy implements PriceStrategy {
    private final double HOURLY_RATE = 10.0;
    private final double HOUR_RATE_ADDED = 5.0;
    @Override
    public double calculatePrice(long durationInMinutes) {
        long hours = (long) Math.ceil(durationInMinutes / 60.0);
        if(hours <= 1){
            return HOURLY_RATE;
        }else {
            return HOURLY_RATE + (hours - 1) * HOUR_RATE_ADDED;
        }
    }
}

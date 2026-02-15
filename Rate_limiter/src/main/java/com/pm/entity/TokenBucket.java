package com.pm.entity;

import java.util.concurrent.locks.ReentrantLock;

public class TokenBucket {

    private final int capacity;
    private int tokensAvailable;
    private long lastRefillTime;
    private final double refillRate;
    private final ReentrantLock lock = new ReentrantLock();

    public TokenBucket(int capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokensAvailable = capacity;
        this.lastRefillTime = System.currentTimeMillis();
    }


    public boolean allowRequest() {
        lock.lock();
        try {
            refill();
            if (tokensAvailable > 0) {
                tokensAvailable--;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }


    private void refill() {
        long currentTime = System.currentTimeMillis();
        long timeElapsed = currentTime - lastRefillTime;

        double tokensToAdd = (timeElapsed / 1000.0) * refillRate;

        if (tokensToAdd > 0) {
            tokensAvailable = Math.min(
                    capacity,
                    tokensAvailable + (int) tokensToAdd
            );
            lastRefillTime = currentTime;
        }
    }
}

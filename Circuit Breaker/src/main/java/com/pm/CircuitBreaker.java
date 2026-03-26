package com.pm;

import com.pm.configs.CircuitBreakerConfig;
import com.pm.enums.State;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class CircuitBreaker {

    private volatile State currentState;

    private final AtomicInteger failureCount = new AtomicInteger(0);
    private final AtomicInteger successCount = new AtomicInteger(0);

    private volatile long lastFailureTime;

    private final CircuitBreakerConfig config;

    public CircuitBreaker(CircuitBreakerConfig config) {
        this.config = config;
        this.currentState = State.CLOSED;
    }

    public <T> T execute(Supplier<T> supplier) {
        if (!allowRequest()) {
            throw new RuntimeException("Circuit is OPEN. Request blocked.");
        }

        try {
            T result = supplier.get();
            recordSuccess();
            return result;
        } catch (Exception e) {
            recordFailure();
            throw e;
        }
    }

    private boolean allowRequest() {
        if (currentState == State.OPEN) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastFailureTime > config.getRetryTimeout()) {
                synchronized (this) {
                    if (currentState == State.OPEN) {
                        currentState = State.HALF_OPEN;
                        successCount.set(0);
                    }
                }
                return true;
            }
            return false;
        }
        return true;
    }

    private void recordSuccess() {
        if (currentState == State.HALF_OPEN) {
            int count = successCount.incrementAndGet();
            if (count >= config.getSuccessThreshold()) {
                synchronized (this) {
                    if (currentState == State.HALF_OPEN) { // re-check inside lock
                        reset();
                    }
                }
            }
        } else {
            failureCount.set(0);
        }
    }

    private void recordFailure() {
        if (currentState == State.HALF_OPEN) {
            synchronized (this) {
                if (currentState == State.HALF_OPEN) {
                    currentState = State.OPEN;
                    lastFailureTime = System.currentTimeMillis(); // reset window on re-open
                }
            }
            return;
        }

        int failures = failureCount.incrementAndGet();
        if (failures >= config.getFailureThreshold()) {
            synchronized (this) {
                if (currentState != State.OPEN) {
                    currentState = State.OPEN;
                    lastFailureTime = System.currentTimeMillis(); // stamp only on first open
                }
            }
        }
    }

    private void reset() {
        currentState = State.CLOSED;
        failureCount.set(0);
        successCount.set(0);
    }

    public State getCurrentState() {
        return currentState;
    }
}
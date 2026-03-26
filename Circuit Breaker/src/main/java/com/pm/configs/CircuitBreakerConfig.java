package com.pm.configs;

public class CircuitBreakerConfig {
    private final int failureThreshold;
    private final int successThreshold;
    private final long retryTimeout;

    public CircuitBreakerConfig(int failureThreshold, int successThreshold, long retryTimeout) {
        this.failureThreshold = failureThreshold;
        this.successThreshold = successThreshold;
        this.retryTimeout = retryTimeout;
    }

    public int getFailureThreshold() {
        return failureThreshold;
    }

    public int getSuccessThreshold() {
        return successThreshold;
    }

    public long getRetryTimeout() {
        return retryTimeout;
    }
}
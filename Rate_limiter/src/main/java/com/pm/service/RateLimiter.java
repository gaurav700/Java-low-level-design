package com.pm.service;

import com.pm.entity.TokenBucket;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RateLimiter {
    ConcurrentHashMap<String, TokenBucket> tokenBuckets;

    public RateLimiter() {
        tokenBuckets = new ConcurrentHashMap<>();
    }

    public boolean hitRequest(String key) {

        TokenBucket bucket =
                tokenBuckets.computeIfAbsent(
                        key,
                        k -> new TokenBucket(10, 1.0)
                );

        return bucket.allowRequest();
    }

}

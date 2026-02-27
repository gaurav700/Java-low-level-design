package com.pm.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IdempotencyService {
    private final Map<String, String> idemptencyMap = new ConcurrentHashMap<>();

    public boolean isDuplicate(String key){
        return idemptencyMap.containsKey(key);
    }

    public void save(String key, String value){
        idemptencyMap.put(key, value);
    }

    public String getPaymentId(String key){
        return idemptencyMap.get(key);
    }
}

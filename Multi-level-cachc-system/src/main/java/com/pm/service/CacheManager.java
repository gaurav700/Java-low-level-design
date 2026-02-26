package com.pm.service;

import com.pm.entity.Cache;
import com.pm.entity.DB;

import java.util.concurrent.ConcurrentHashMap;

public class CacheManager<K, V> {

    private final Cache<K, V> l1;
    private final Cache<K, V> l2;
    private final DB<K, V> database;

    private final ConcurrentHashMap<K, Object> keyLocks = new ConcurrentHashMap<>();

    public CacheManager(Cache<K, V> l1,
                        Cache<K, V> l2,
                        DB<K, V> database) {
        this.l1 = l1;
        this.l2 = l2;
        this.database = database;
    }

    public void put(K key, V value) {
        l1.put(key, value);
        l2.put(key, value);
    }

    public V get(K key) {

        // 1️⃣ Fast path (no locking)
        V value = l1.get(key);
        if (value != null) {
            return value;
        }

        value = l2.get(key);
        if (value != null) {
            l1.put(key, value);
            return value;
        }

        // 2️⃣ Lock per key to prevent stampede
        Object lock = keyLocks.computeIfAbsent(key, k -> new Object());

        synchronized (lock) {

            // Double-check after acquiring lock
            value = l1.get(key);
            if (value != null) {
                return value;
            }

            value = l2.get(key);
            if (value != null) {
                l1.put(key, value);
                return value;
            }

            // Fetch from DB
            value = database.get(key);
            if (value != null) {
                l2.put(key, value);
                l1.put(key, value);
            }

            keyLocks.remove(key); // cleanup

            return value;
        }
    }
}
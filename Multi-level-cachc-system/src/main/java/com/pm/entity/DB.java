package com.pm.entity;

import java.util.HashMap;
import java.util.Map;

public class DB<K, V> {

    private final Map<K, V> storage = new HashMap<>();

    public DB() {
        storage.put((K)"X", (V)"ExpensiveData");
    }

    public V get(K key) {
        System.out.println("Fetching from DB by "
                + Thread.currentThread().getName());

        try {
            Thread.sleep(2000); // simulate slow DB
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return storage.get(key);
    }
}
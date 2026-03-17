//package com.pm;
//
//import com.pm.com.pm.entity.Cache;
//import com.pm.com.pm.entity.DB;
//import com.pm.com.pm.entity.LRUCache;
//import com.pm.service.CacheManager;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        // Create L1 (small, fast)
//        Cache<String, String> l1 = new LRUCache<>(2);
//
//        // Create L2 (larger)
//        Cache<String, String> l2 = new LRUCache<>(4);
//
//        // Create mock DB
//        DB<String, String> db = new DB<>();
//
//        // Create Cache Manager
//        CacheManager<String, String> cacheManager =
//                new CacheManager<>(l1, l2, db);
//
//        System.out.println("===== PUT OPERATIONS =====");
//        cacheManager.put("A", "Apple");
//        cacheManager.put("B", "Banana");
//        cacheManager.put("C", "Cherry");  // This should evict from L1 (capacity=2)
//
//        System.out.println("\n===== GET OPERATIONS =====");
//
//        // L1 hit
//        System.out.println("Get A: " + cacheManager.get("A"));
//
//        // L2 hit → promote to L1
//        System.out.println("Get B: " + cacheManager.get("B"));
//
//        // Miss in both → DB fallback
//        System.out.println("Get Z: " + cacheManager.get("Z"));
//
//        System.out.println("\n===== END SIMULATION =====");
//    }
//}


package com.pm;

import com.pm.entity.Cache;
import com.pm.entity.DB;
import com.pm.entity.LRUCache;
import com.pm.service.CacheManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        Cache<String, String> l1 = new LRUCache<>(2);
        Cache<String, String> l2 = new LRUCache<>(5);
        DB<String, String> db = new DB<>();

        CacheManager<String, String> cacheManager =
                new CacheManager<>(l1, l2, db);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        System.out.println("Starting concurrent access...\n");

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                String value = cacheManager.get("X");
                System.out.println(Thread.currentThread().getName()
                        + " got value: " + value);
            });
        }

        executor.shutdown();
    }
}
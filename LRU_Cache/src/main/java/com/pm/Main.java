//package com.pm;
//
//import com.pm.service.LRUCache;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        LRUCache cache = new LRUCache(2);
//
//        System.out.println("Put(1,10)");
//        cache.put(1, 10);
//
//        System.out.println("Put(2,20)");
//        cache.put(2, 20);
//
//        System.out.println("Get(1): " + cache.get(1)); // 10
//
//        System.out.println("Put(3,30)  -> Evicts 2");
//        cache.put(3, 30);
//
//        System.out.println("Get(2): " + cache.get(2)); // -1
//
//        System.out.println("Put(4,40)  -> Evicts 1");
//        cache.put(4, 40);
//
//        System.out.println("Get(1): " + cache.get(1)); // -1
//        System.out.println("Get(3): " + cache.get(3)); // 30
//        System.out.println("Get(4): " + cache.get(4)); // 40
//    }
//}

package com.pm;

import com.pm.service.LRUCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        LRUCache cache = new LRUCache(3);

        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        System.out.println("Starting concurrent access...\n");

        for (int i = 0; i < threadCount; i++) {
            int key = i % 5; // keys 0–4

            executor.execute(() -> {

                cache.put(key, key * 100);

                int value = cache.get(key);

                System.out.println(Thread.currentThread().getName()
                        + " -> Key: " + key
                        + ", Value: " + value);

                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        System.out.println("\nAll threads completed.");
    }
}

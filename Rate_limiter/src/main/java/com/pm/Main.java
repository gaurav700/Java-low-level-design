//package com.pm;
//
//import com.pm.service.RateLimiter;
//
//public class Main {
//
//    public static void main(String[] args) throws InterruptedException {
//
//        RateLimiter rateLimiter = new RateLimiter();
//
//        String user = "user1";
//        for (int i = 1; i <= 15; i++) {
//            boolean allowed = rateLimiter.hitRequest(user);
//            System.out.println("Request " + i + " -> " + allowed);
//        }
//        System.out.println("Waiting 5 seconds for refill...\n");
//        Thread.sleep(5000);
//        for (int i = 16; i <= 20; i++) {
//            boolean allowed = rateLimiter.hitRequest(user);
//            System.out.println("Request " + i + " -> " + allowed);
//        }
//
//
//    }
//}


package com.pm;

import com.pm.service.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        RateLimiter rateLimiter = new RateLimiter();

        String user = "user1";

        int threadCount = 50;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        System.out.println("Sending 50 concurrent requests...\n");

        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                boolean allowed = rateLimiter.hitRequest(user);
                System.out.println(Thread.currentThread().getName()
                        + " -> " + allowed);
                latch.countDown();
            });
        }

        latch.await(); // wait for all threads
        executor.shutdown();

        System.out.println("\nAll requests processed.");
    }
}

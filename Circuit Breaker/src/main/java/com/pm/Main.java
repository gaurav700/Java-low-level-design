//package com.pm;
//
//import com.pm.configs.CircuitBreakerConfig;
//
//public class Main {
//
//    // Fail for the first 6 seconds, then succeed — independent of blocked calls
//    private static final long START_TIME = System.currentTimeMillis();
//
//    public static void main(String[] args) {
//
//        // CircuitBreakerConfig(failureThreshold=3, successThreshold=2, retryTimeout=5000ms)
//        CircuitBreakerConfig config = new CircuitBreakerConfig(3, 2, 5000);
//
//        CircuitBreaker cb = new CircuitBreaker(config);
//
//        for (int i = 1; i <= 15; i++) {
//            try {
//                String response = cb.execute(() -> mockApiCall());
//                System.out.println("Call " + i + " SUCCESS: " + response + " | State: " + cb.getCurrentState());
//            } catch (Exception e) {
//                System.out.println("Call " + i + " FAILURE: " + e.getMessage() + " | State: " + cb.getCurrentState());
//            }
//
//            sleep(1000);
//        }
//    }
//
//    private static String mockApiCall() {
//        if (System.currentTimeMillis() - START_TIME < 6000) {
//            throw new RuntimeException("API Failure");
//        }
//        return "API Success";
//    }
//
//    private static void sleep(int ms) {
//        try {
//            Thread.sleep(ms);
//        } catch (InterruptedException ignored) {}
//    }
//}

package com.pm;

import com.pm.configs.CircuitBreakerConfig;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    // Fail for the first 6 seconds, then succeed
    private static final long START_TIME = System.currentTimeMillis();

    // Track how many actual API calls got through (not blocked)
    private static final AtomicInteger successCount = new AtomicInteger(0);
    private static final AtomicInteger failureCount = new AtomicInteger(0);
    private static final AtomicInteger blockedCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        // failureThreshold=3, successThreshold=2, retryTimeout=5000ms
        CircuitBreakerConfig config = new CircuitBreakerConfig(3, 2, 5000);
        CircuitBreaker cb = new CircuitBreaker(config);

        int totalThreads = 20;
        int totalCalls = 60; // 60 tasks spread across 20 threads

        ExecutorService executor = Executors.newFixedThreadPool(totalThreads);
        CountDownLatch latch = new CountDownLatch(totalCalls);

        System.out.println("=".repeat(70));
        System.out.printf("%-6s %-18s %-12s %-12s %s%n",
                "Call", "Thread", "Result", "State", "Message");
        System.out.println("=".repeat(70));

        AtomicInteger callId = new AtomicInteger(0);

        for (int i = 0; i < totalCalls; i++) {
            final int delayMs = i * 300; // stagger calls 300ms apart

            executor.submit(() -> {
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException ignored) {}

                int id = callId.incrementAndGet();
                String threadName = Thread.currentThread().getName();

                try {
                    String response = cb.execute(() -> mockApiCall());
                    successCount.incrementAndGet();
                    System.out.printf("%-6d %-18s %-12s %-12s %s%n",
                            id, threadName, "✅ SUCCESS", cb.getCurrentState(), response);
                } catch (Exception e) {
                    String msg = e.getMessage();
                    if (msg.contains("blocked")) {
                        blockedCount.incrementAndGet();
                        System.out.printf("%-6d %-18s %-12s %-12s %s%n",
                                id, threadName, "🚫 BLOCKED", cb.getCurrentState(), msg);
                    } else {
                        failureCount.incrementAndGet();
                        System.out.printf("%-6d %-18s %-12s %-12s %s%n",
                                id, threadName, "❌ FAILURE", cb.getCurrentState(), msg);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // wait for all tasks to finish
        executor.shutdown();

        // Summary
        System.out.println("=".repeat(70));
        System.out.println("SIMULATION COMPLETE");
        System.out.println("=".repeat(70));
        System.out.println("  Total Calls Submitted : " + totalCalls);
        System.out.println("  ✅ Successes          : " + successCount.get());
        System.out.println("  ❌ Failures (API)     : " + failureCount.get());
        System.out.println("  🚫 Blocked (OPEN)     : " + blockedCount.get());
        System.out.println("  Final State           : " + cb.getCurrentState());
        System.out.println("=".repeat(70));
    }

    private static String mockApiCall() {
        long elapsed = System.currentTimeMillis() - START_TIME;

        // Fail for first 6 seconds, succeed after
        if (elapsed < 6000) {
            throw new RuntimeException("API Failure");
        }
        return "API Success";
    }
}
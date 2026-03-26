package com.pm;

public class Consumer implements Runnable {
    private final SharedQueue<String> queue;
    private final String name;

    public Consumer(SharedQueue<String> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        while(true) {
            String task = queue.take();
            if (task == null || task.equals("POISON_PILL")) {
                System.out.println("[STOPPED] " + name
                        + " received shutdown signal");
                break;
            }

            System.out.println("[CONSUMED] " + name
                    + " → " + task
                    + " | Queue size: " + queue.size());

            try {
                Thread.sleep(1000); // consumers slower than producers
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

        }
    }
}

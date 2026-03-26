package com.pm;

public class Producer implements Runnable{
    private final SharedQueue<String> queue;
    private final String name;
    private final int numTasks;
    private final int numConsumers;


    public Producer(SharedQueue<String> queue, String name, int numTasks, int numConsumers) {
        this.queue = queue;
        this.name = name;
        this.numTasks = numTasks;
        this.numConsumers = numConsumers;
    }

    @Override
    public void run() {
        for(int i = 0; i < numTasks; i++){
            queue.put("Task "+ i);
            System.out.println("[PRODUCED] " + name
                    + " → Task-" + i
                    + " | Queue size: " + queue.size());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        for (int i = 0; i < numConsumers; i++) {
            queue.put("POISON_PILL");
            System.out.println("[SHUTDOWN] " + name
                    + " sent POISON_PILL " + (i + 1)
                    + "/" + numConsumers);
        }

    }
}

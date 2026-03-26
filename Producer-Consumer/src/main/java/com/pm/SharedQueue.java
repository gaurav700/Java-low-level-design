package com.pm;

import java.util.concurrent.LinkedBlockingQueue;

public class SharedQueue<T> {
    private final LinkedBlockingQueue<T> queue;

    public SharedQueue(int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    public T take() {
        try{
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public void put(T t) {
        try{
            queue.put(t);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int size() {
        return queue.size();
    }

}

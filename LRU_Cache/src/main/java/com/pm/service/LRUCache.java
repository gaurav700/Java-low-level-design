package com.pm.service;

import com.pm.entity.Node;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache {

    HashMap<Integer, Node> map;
    private final Node head = new Node(-1, -1);
    private final Node tail = new Node(-1, -1);
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    public void put(int key, int value) {
        lock.lock();
        try {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                moveToHead(node);
                return;
            }

            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);

            if (map.size() > capacity) {
                Node tailNode = tail.prev;
                map.remove(tailNode.key);
                removeNode(tailNode);
            }
        }
        finally {
            lock.unlock();
        }
    }

    public int get(int key) {
        lock.lock();
        try{
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        moveToHead(node);
        return node.value;
        }
        finally {
            lock.unlock();
        }
    }

    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public void moveToHead(Node node){
        removeNode(node);
        addToHead(node);
    }
}

package com.pm.entity;

public class Node {
    public int key;
    public int value;
    public Node next;
    public Node prev;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }

}

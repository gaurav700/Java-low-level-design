package com.pm.entity;

import java.util.UUID;

public class MenuItem {

    private final String itemId;
    private final String name;
    private final double price;

    public MenuItem(String name, double price) {
        this.itemId = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
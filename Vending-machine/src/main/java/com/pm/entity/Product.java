package com.pm.entity;

import java.util.UUID;

public class Product {
    private final String id;
    private final String name;
    private double price;

    public Product(String name, double price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

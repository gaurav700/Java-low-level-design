package com.pm.entity;

import java.util.*;

public class Restaurant {

    private final String restaurantId;
    private final String name;
    private final Location location;
    private final List<MenuItem> menuItems;

    public Restaurant(String name, Location location) {
        this.restaurantId = UUID.randomUUID().toString();
        this.name = name;
        this.location = location;
        this.menuItems = new ArrayList<>();
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public List<MenuItem> getMenuItems() {
        return Collections.unmodifiableList(menuItems);
    }
}
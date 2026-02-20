package com.pm.services;

import com.pm.entity.*;
import java.util.*;

public class RestaurantService {

    private final Map<String, Restaurant> restaurants = new HashMap<>();

    public Restaurant registerRestaurant(String name, Location location) {
        Restaurant restaurant = new Restaurant(name, location);
        restaurants.put(restaurant.getRestaurantId(), restaurant);
        return restaurant;
    }

    public void addMenuItem(String restaurantId, MenuItem item) {
        Restaurant restaurant = restaurants.get(restaurantId);

        if (restaurant == null) {
            throw new IllegalStateException("Restaurant not found");
        }

        restaurant.addMenuItem(item);
    }

    public Restaurant getRestaurant(String id) {
        return restaurants.get(id);
    }
}
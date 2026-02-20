package com.pm.entity;

import java.util.*;

public class Cart {

    private final String userId;
    private String restaurantId;
    private final Map<String, CartItem> items = new HashMap<>();

    public Cart(String userId) {
        this.userId = userId;
    }

    public void addItem(MenuItem item, int quantity, String restaurantId) {

        if (this.restaurantId != null &&
                !this.restaurantId.equals(restaurantId)) {
            throw new IllegalStateException("Single restaurant per cart");
        }

        this.restaurantId = restaurantId;

        items.compute(item.getItemId(), (k, v) -> {
            if (v == null) return new CartItem(item, quantity);
            v.increaseQuantity(quantity);
            return v;
        });
    }

    public double calculateTotal() {
        return items.values()
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public String getRestaurantId() { return restaurantId; }

    public void clear() {
        items.clear();
        restaurantId = null;
    }
}
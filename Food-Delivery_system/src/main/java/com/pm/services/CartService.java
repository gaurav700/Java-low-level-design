package com.pm.services;

import com.pm.entity.*;

import java.util.*;

public class CartService {

    private final Map<String, Cart> carts = new HashMap<>();

    public Cart addToCart(String userId,
                          String restaurantId,
                          MenuItem item,
                          int quantity) {

        Cart cart = carts.computeIfAbsent(userId, Cart::new);
        cart.addItem(item, quantity, restaurantId);
        return cart;
    }

    public Cart getCart(String userId) {
        return carts.get(userId);
    }

    public void clearCart(String userId) {
        Cart cart = carts.get(userId);
        if (cart != null) cart.clear();
    }
}
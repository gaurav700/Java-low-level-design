package com.pm.service;

import com.pm.entity.InventoryItem;
import com.pm.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<String, InventoryItem> inventory = new HashMap<>();

    // Add new product or restock existing
    public String addProduct(String name, int price, int quantity) {

        Product product = new Product(name, price);

        InventoryItem inventoryItem =
                new InventoryItem(product, quantity);

        inventory.put(product.getId(), inventoryItem);

        System.out.println("Product added with id: " + product.getId());
        return product.getId();
    }

    public boolean isAvailable(String productId) {

        InventoryItem item = inventory.get(productId);

        if (item == null) {
            throw new RuntimeException("Invalid product id");
        }

        return item.getQuantity() > 0;
    }

    public Product getProduct(String productId) {

        InventoryItem item = inventory.get(productId);

        if (item == null) {
            throw new RuntimeException("Invalid product id");
        }

        return item.getProduct();
    }

    public void deduct(String productId) {

        InventoryItem item = inventory.get(productId);

        if (item == null) {
            throw new RuntimeException("Invalid product id");
        }

        if (item.getQuantity() <= 0) {
            throw new RuntimeException("Product out of stock");
        }

        item.decreaseQuantity();
    }

    public void restock(String productId, int quantity) {

        InventoryItem item = inventory.get(productId);

        if (item == null) {
            throw new RuntimeException("Invalid product id");
        }

        item.increaseQuantity(quantity);
    }
}
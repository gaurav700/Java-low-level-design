package com.pm.entity;

public class InventoryItem {

    private final Product product;
    private int quantity;

    public InventoryItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public void increaseQuantity(int count) {
        quantity += count;
    }
}
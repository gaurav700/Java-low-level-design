package com.pm.entity;

public class CartItem {

    private final String itemId;
    private final String name;
    private final double price;
    private int quantity;

    public CartItem(MenuItem item, int quantity) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    public void increaseQuantity(int qty) {
        this.quantity += qty;
    }

    public String getItemId() { return itemId; }
    public int getQuantity() { return quantity; }
}
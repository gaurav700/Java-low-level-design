package com.pm.SingleResponsbilityPrinciple;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
    private List<String> orders = new ArrayList<>();
    InventoryManger inventoryManger;
    NotificationService notificationService;
    double PRICE_PER_UNIT = 100.0;

    public OrderProcessor(InventoryManger inventoryManger, NotificationService notificationService) {
        this.inventoryManger = inventoryManger;
        this.notificationService = notificationService;
    }

    public void placeOrder(String productId, int quantity, String customerEmail) {
        // Responsibility 1: Inventory check
        if (inventoryManger.checkInventory(productId, quantity)) {
            System.out.println("Insufficient stock for " + productId);
            return;
        }

        // Responsibility 2: Order processing
        double total = PRICE_PER_UNIT * quantity;
        String orderId = "ORD-" + (orders.size() + 1);
        orders.add(orderId);

        // Responsibility 3: Update inventory
        inventoryManger.updateInventory(productId, quantity);

        // Responsibility 4: Send notification
        notificationService.sendNotification(customerEmail, orderId, total);
    }

}

package com.pm.entity;

import com.pm.enums.OrderStatus;
import java.util.*;

public class Order {

    private final String orderId;
    private final String userId;
    private final String restaurantId;
    private final List<CartItem> items;

    private double totalPrice;
    private OrderStatus status;
    private String deliveryPartnerId;

    public Order(String userId,
                 String restaurantId,
                 List<CartItem> cartItems,
                 double totalPrice) {

        this.orderId = UUID.randomUUID().toString();
        this.userId = userId;
        this.restaurantId = restaurantId;

        // snapshot copy
        this.items = new ArrayList<>(cartItems);
        this.totalPrice = totalPrice;
        this.status = OrderStatus.CREATED;
    }

    public void accept() { status = OrderStatus.ACCEPTED; }
    public void markPreparing() { status = OrderStatus.PREPARING; }
    public void markReady() { status = OrderStatus.READY; }

    public void assignDeliveryPartner(String partnerId) {
        this.deliveryPartnerId = partnerId;
        status = OrderStatus.DELIVERY_ASSIGNED;
    }

    public void markPickedUp() { status = OrderStatus.PICKED_UP; }
    public void markDelivered() { status = OrderStatus.DELIVERED; }

    public String getOrderId() { return orderId; }
    public OrderStatus getStatus() { return status; }
    public String getUserId() { return userId; }
}
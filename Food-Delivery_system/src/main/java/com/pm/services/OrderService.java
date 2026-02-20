package com.pm.services;

import com.pm.entity.*;

import java.util.*;

public class OrderService {

    private final Map<String, Order> orders = new HashMap<>();
    private final CartService cartService;
    private final DeliveryPartnerService partnerService;

    public OrderService(CartService cartService,
                        DeliveryPartnerService partnerService) {
        this.cartService = cartService;
        this.partnerService = partnerService;
    }

    public Order checkout(String userId) {

        Cart cart = cartService.getCart(userId);

        Order order = new Order(
                userId,
                cart.getRestaurantId(),
                cart.getItems(),
                cart.calculateTotal()
        );

        orders.put(order.getOrderId(), order);
        cartService.clearCart(userId);

        return order;
    }

    public void assignDelivery(String orderId) {
        Order order = orders.get(orderId);
        DeliveryPartner partner = partnerService.getAvailablePartner();
        order.assignDeliveryPartner(partner.getPartnerId());
        partner.markBusy();
    }
}
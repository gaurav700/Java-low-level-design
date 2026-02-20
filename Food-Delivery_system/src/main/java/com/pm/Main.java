package com.pm;

import com.pm.entity.*;
import com.pm.services.*;

public class Main {

    public static void main(String[] args) {

        // ===============================
        // Initialize Services
        // ===============================

        UserService userService = new UserService();
        RestaurantService restaurantService = new RestaurantService();
        CartService cartService = new CartService();
        DeliveryPartnerService partnerService = new DeliveryPartnerService();
        OrderService orderService =
                new OrderService(cartService, partnerService);

        // ===============================
        // 1. Register Restaurant
        // ===============================

        Restaurant restaurant =
                restaurantService.registerRestaurant(
                        "Punjabi Dhaba",
                        new Location(10.0, 20.0)
                );

        MenuItem chole =
                new MenuItem("Chole Bhature", 120.0);

        MenuItem lassi =
                new MenuItem("Sweet Lassi", 60.0);

        restaurantService.addMenuItem(
                restaurant.getRestaurantId(), chole);

        restaurantService.addMenuItem(
                restaurant.getRestaurantId(), lassi);

        System.out.println("Restaurant registered: "
                + restaurant.getName());

        // ===============================
        // 2. Register User
        // ===============================

        User user =
                userService.register(
                        "Gaurav",
                        "gaurav@email.com",
                        "1234",
                        new Location(11.0, 21.0)
                );

        System.out.println("User registered: "
                + user.getUserId());

        // ===============================
        // 3. Add Items to Cart
        // ===============================

        cartService.addToCart(
                user.getUserId(),
                restaurant.getRestaurantId(),
                chole,
                2
        );

        cartService.addToCart(
                user.getUserId(),
                restaurant.getRestaurantId(),
                lassi,
                1
        );

        System.out.println("Items added to cart");

        // ===============================
        // 4. Checkout
        // ===============================

        Order order =
                orderService.checkout(user.getUserId());

        System.out.println("Order created: "
                + order.getOrderId());

        // ===============================
        // 5. Restaurant Flow
        // ===============================

        order.accept();
        order.markPreparing();
        order.markReady();

        System.out.println("Order is READY");

        // ===============================
        // 6. Register Delivery Partner
        // ===============================

        DeliveryPartner partner =
                partnerService.registerPartner("Rahul");

        // Assign delivery automatically
        orderService.assignDelivery(order.getOrderId());

        System.out.println("Delivery partner assigned");

        // ===============================
        // 7. Delivery Flow
        // ===============================

        order.markPickedUp();
        order.markDelivered();

        System.out.println("Order delivered successfully!");

        // ===============================
        // Final Status
        // ===============================

        System.out.println("Final Order Status: "
                + order.getStatus());
    }
}
package com.pm.DependencyInjectionPrinciple;

public class OrderService {

    private final Database database;
    public OrderService(Database database) {
        this.database = database;
    }

    public void placeOrder(String orderId, String orderData){
        System.out.println("Placing order: "+ orderId);
        database.insert("Orders", orderData);
        System.out.println("Order placed Successfully");
    }

    public String getOrder(String orderId){
        return database.query("Orders", orderId);
    }
}

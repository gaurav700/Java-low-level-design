package com.pm.SingleResponsbilityPrinciple;

public class NotificationService {
    public void sendNotification(String customerEmail, String orderId, double total){
        System.out.println("Email to " + customerEmail + ": Order " + orderId
                + " confirmed. Total: $" + total);
    }
}

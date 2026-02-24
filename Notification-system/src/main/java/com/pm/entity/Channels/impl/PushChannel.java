package com.pm.entity.Channels.impl;

import com.pm.entity.*;
import com.pm.entity.Channels.*;

public class PushChannel implements NotificationChannel {

    @Override
    public void send(Notification notification, User user) {

        System.out.println("Sending PUSH notification to user: " + user.getName());
        System.out.println("Title: " + notification.getTitle());
        System.out.println("Message: " + notification.getMessage());

        NotificationDelivery delivery =
                new NotificationDelivery(
                        notification.getNotificationID(),
                        ChannelType.PUSH
                );

        try {
            delivery.markDelivered();
            System.out.println("Push Notification Delivered Successfully\n");
        } catch (Exception e) {
            delivery.markFailed();
            System.out.println("Push Notification Delivery Failed\n");
        }
    }
}
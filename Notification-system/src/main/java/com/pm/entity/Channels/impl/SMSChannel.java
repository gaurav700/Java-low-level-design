package com.pm.entity.Channels.impl;

import com.pm.entity.*;
import com.pm.entity.Channels.*;

public class SMSChannel implements NotificationChannel {

    @Override
    public void send(Notification notification, User user) {

        System.out.println("Sending SMS to user: " + user.getName());
        System.out.println("Message: " + notification.getMessage());

        NotificationDelivery delivery =
                new NotificationDelivery(
                        notification.getNotificationID(),
                        ChannelType.SMS
                );

        try {
            delivery.markDelivered();
            System.out.println("SMS Delivered Successfully\n");
        } catch (Exception e) {
            delivery.markFailed();
            System.out.println("SMS Delivery Failed\n");
        }
    }
}
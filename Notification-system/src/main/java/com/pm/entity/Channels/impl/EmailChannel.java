package com.pm.entity.Channels.impl;

import com.pm.entity.Channels.ChannelType;
import com.pm.entity.Channels.NotificationChannel;
import com.pm.entity.Notification;
import com.pm.entity.NotificationDelivery;
import com.pm.entity.User;

public class EmailChannel implements NotificationChannel {
    @Override
    public void send(Notification notification, User user) {
        System.out.println("Sending EMAIL to "+user.getEmail());
        System.out.println("Title: "+notification.getTitle());
        System.out.println("Message:  "+notification.getMessage());

        NotificationDelivery delivery = new NotificationDelivery((notification.getNotificationID()), ChannelType.EMAIL);

        try {
            delivery.markDelivered();
            System.out.println("Email Notification Delivered Successfully\n");
        } catch (Exception e) {
            delivery.markFailed();
            System.out.println("Email Notification Delivery Failed\n");
        }
    }
}

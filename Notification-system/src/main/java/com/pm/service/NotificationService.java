package com.pm.service;

import com.pm.entity.*;
import com.pm.entity.Channels.*;
import com.pm.utils.ChannelFactory;

public class NotificationService {

    public void sendNotification(User user, Notification notification) {

        if (user.getNotificationPreference() == null) {
            System.out.println("No preferences set for user.");
            return;
        }

        for (ChannelType type :
                user.getNotificationPreference().getEnabledChannels()) {

            NotificationChannel channel =
                    ChannelFactory.getChannel(type);

            try {
                channel.send(notification, user);
            } catch (Exception e) {
                System.out.println("Failed to send via " + type);
            }
        }
    }
}
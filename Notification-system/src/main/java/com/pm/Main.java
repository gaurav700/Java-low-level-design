package com.pm;

import com.pm.entity.*;
import com.pm.entity.Channels.ChannelType;
import com.pm.service.NotificationService;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // ===============================
        // 1️⃣ Create Users
        // ===============================

        User user1 = new User("Alice", "alice@example.com");
        User user2 = new User("Bob", "bob@example.com");
        User user3 = new User("Charlie", "charlie@example.com");

        // ===============================
        // 2️⃣ Set Preferences
        // ===============================

        user1.setNotificationPreference(
                new NotificationPreference(
                        Set.of(ChannelType.EMAIL, ChannelType.PUSH)
                )
        );

        user2.setNotificationPreference(
                new NotificationPreference(
                        Set.of(ChannelType.SMS)
                )
        );

        user3.setNotificationPreference(
                new NotificationPreference(
                        Set.of(ChannelType.EMAIL, ChannelType.SMS, ChannelType.PUSH)
                )
        );

        // ===============================
        // 3️⃣ Create Notification
        // ===============================

        Notification notification =
                new Notification(
                        "System Maintenance",
                        "We will be down from 2 AM to 4 AM."
                );

        // ===============================
        // 4️⃣ Create NotificationService
        // ===============================

        NotificationService service = new NotificationService();

        // ===============================
        // 5️⃣ Send to Single User
        // ===============================

        System.out.println("Sending to Alice:\n");
        service.sendNotification(user1, notification);

        // ===============================
        // 6️⃣ Bulk Send
        // ===============================

        System.out.println("Bulk Sending:\n");

        List<User> users = Arrays.asList(user1, user2, user3);

        for (User user : users) {
            service.sendNotification(user, notification);
        }

        System.out.println("Notification Simulation Completed.");
    }
}
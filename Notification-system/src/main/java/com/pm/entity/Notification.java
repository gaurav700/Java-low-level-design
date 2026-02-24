package com.pm.entity;

import java.util.UUID;

public class Notification {
    private final String notificationID;
    private String title;
    private String message;
    private long createdAt;


    public Notification(String title, String message) {
        this.notificationID = UUID.randomUUID().toString();
        this.title = title;
        this.message = message;
        this.createdAt = System.currentTimeMillis();
    }

    public String getNotificationID() {
        return notificationID;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public long getCreatedAt() {
        return createdAt;
    }

}

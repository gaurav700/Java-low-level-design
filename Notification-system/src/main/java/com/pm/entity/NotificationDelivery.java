package com.pm.entity;

import com.pm.entity.Channels.ChannelType;

public class NotificationDelivery {
    private final String notificationId;
    private ChannelType channelType;;
    private NotificationStatus status;
    private long deliveryTime;

    public NotificationDelivery(String notificationId, ChannelType channelType) {
        this.notificationId = notificationId;
        this.channelType = channelType;
        this.status = NotificationStatus.IN_PROGRESS;
    }

    public void markDelivered(){
        this.status = NotificationStatus.DELIVERED;
        this.deliveryTime = System.currentTimeMillis();
    }

    public void markFailed(){
        this.status = NotificationStatus.SENT;
    }
}

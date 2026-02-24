package com.pm.entity.Channels;

import com.pm.entity.Notification;
import com.pm.entity.User;

public interface NotificationChannel {
    void send(Notification notification, User user);
}

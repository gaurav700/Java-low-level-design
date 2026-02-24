package com.pm.utils;

import com.pm.entity.Channels.ChannelType;
import com.pm.entity.Channels.NotificationChannel;
import com.pm.entity.Channels.impl.EmailChannel;
import com.pm.entity.Channels.impl.PushChannel;
import com.pm.entity.Channels.impl.SMSChannel;

public class ChannelFactory {
    public static NotificationChannel getChannel(ChannelType type) {
        switch (type) {
            case SMS:
                return new SMSChannel();
            case EMAIL:
                return new EmailChannel();
            case PUSH:
                return new PushChannel();
                default:
                    throw new IllegalArgumentException("Invalid channel type");
        }
    }
}

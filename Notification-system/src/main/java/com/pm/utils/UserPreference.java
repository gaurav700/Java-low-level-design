package com.pm.utils;

import com.pm.entity.Channels.ChannelType;
import com.pm.entity.NotificationPreference;
import com.pm.entity.User;

import java.util.HashMap;
import java.util.Set;

public class UserPreference {
        public void updatePreference(User user, Set<ChannelType> channels) {
            user.setNotificationPreference(
                    new NotificationPreference(channels)
            );
        }
}
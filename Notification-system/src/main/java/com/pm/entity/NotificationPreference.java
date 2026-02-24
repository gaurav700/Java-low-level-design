package com.pm.entity;

import com.pm.entity.Channels.ChannelType;

import java.util.Set;

public class NotificationPreference {
    private final Set<ChannelType> enabledChannels;

    public NotificationPreference(Set<ChannelType> enabledChannels) {
        this.enabledChannels = enabledChannels;
    }

    public Set<ChannelType> getEnabledChannels() {
        return enabledChannels;
    }
}

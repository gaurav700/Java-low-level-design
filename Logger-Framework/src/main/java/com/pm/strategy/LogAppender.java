package com.pm.strategy;

import com.pm.entity.LogMessage;

public interface LogAppender {
    void append(LogMessage message);
    void close();
    LogFormatter getFormatter();
    void setFormatter(LogFormatter formatter);
}

package com.pm.entity;

import com.pm.enums.LogLevel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class LogMessage {
    private LogLevel level;
    private String message;
    private String ThreadName;
    private LocalDateTime timestamp;
    private String loggerName;


    public LogMessage(LogLevel level, String name, String message) {
        this.level = level;
        this.message = message;
        this.ThreadName = Thread.currentThread().getName();
        this.timestamp = LocalDateTime.now();
        this.loggerName = name;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public String getThreadName() {
        return ThreadName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLoggerName() {
        return loggerName;
    }
}

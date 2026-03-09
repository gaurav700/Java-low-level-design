package com.pm.strategy.impl;

import com.pm.entity.LogMessage;
import com.pm.enums.LogLevel;
import com.pm.strategy.LogFormatter;

import java.time.format.DateTimeFormatter;

public class ColorConsoleFormatter implements LogFormatter {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String PURPLE = "\u001B[35m";

    @Override
    public String format(LogMessage message) {

        String color = getColor(message.getLevel());

        return color + String.format(
                "%s [%s] %s - %s: %s",
                message.getTimestamp().format(FORMATTER),
                message.getThreadName(),
                message.getLevel(),
                message.getLoggerName(),
                message.getMessage()
        ) + RESET;
    }

    private String getColor(LogLevel level) {

        return switch (level) {
            case DEBUG -> BLUE;
            case INFO -> GREEN;
            case WARN -> YELLOW;
            case ERROR -> RED;
            case FATAL -> PURPLE;
        };
    }
}
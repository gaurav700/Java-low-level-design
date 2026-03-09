package com.pm.strategy.impl;

import com.pm.entity.LogMessage;
import com.pm.strategy.LogAppender;
import com.pm.strategy.LogFormatter;

public class ConsoleLogAppender implements LogAppender {

    private LogFormatter formatter;
    public ConsoleLogAppender() {
        this.formatter = new SimpleTextFormatter();
    }

    @Override
    public void append(LogMessage message) {
        System.out.println(formatter.format(message));
    }

    @Override
    public void close() {

    }

    @Override
    public LogFormatter getFormatter() {
        return formatter;
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }
}

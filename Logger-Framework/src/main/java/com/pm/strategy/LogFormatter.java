package com.pm.strategy;

import com.pm.entity.LogMessage;

public interface LogFormatter {
    String format(LogMessage message);
}

package com.pm;

import com.pm.entity.LogMessage;
import com.pm.enums.LogLevel;
import com.pm.strategy.LogAppender;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {
    private final List<LogAppender> appenders;
    private final String name;
    private LogLevel level;
    private boolean additivity;
    private final Logger parent;

    public Logger(String name, Logger parent) {
        this.appenders = new CopyOnWriteArrayList<>();
        this.name = name;
        this.parent = parent;
        this.additivity = true;
    }


    public void calLAppender(LogMessage logMessage){

        if(!appenders.isEmpty()){
            LogManager.getInstance()
                    .getProcessor()
                    .process(logMessage, this.appenders);
        }

        if(additivity && parent != null){
            parent.calLAppender(logMessage);
        }
    }

    public void addAppender(LogAppender appender){
        appenders.add(appender);
    }

    public List<LogAppender> getAppenders() {
        return appenders;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public LogLevel getEffectiveLevel() {
        for (Logger logger = this; logger != null; logger = logger.parent) {
            LogLevel currentLevel = logger.level;
            if (currentLevel != null) {
                return currentLevel;
            }
        }
        return LogLevel.DEBUG; // Default root level
    }

    public void setAdditivity(boolean additivity){
        this.additivity = additivity;
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    public void log(LogLevel level, String message){
        if(level.isGreaterOrEqual(getEffectiveLevel())){
            LogMessage logMessage = new LogMessage(level, this.name, message);
            calLAppender(logMessage);
        }
    }


}

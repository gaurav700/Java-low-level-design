package com.pm.strategy.impl;

import com.pm.entity.LogMessage;
import com.pm.strategy.LogAppender;
import com.pm.strategy.LogFormatter;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogAppender implements LogAppender {
    private FileWriter fileWriter;
    private LogFormatter formatter;

    public FileLogAppender(String filePath) {
        this.formatter = new SimpleTextFormatter();
        try{
            this.fileWriter = new FileWriter(filePath, true);
        } catch (Exception e) {
            System.out.println("Failed to create writer for file logs, exception: " + e.getMessage());
        }
    }

    @Override
    public synchronized void append(LogMessage message) {
        try{
            fileWriter.write(formatter.format(message)+ "\n");
            fileWriter.flush();
        }catch (IOException e) {
            System.out.println("Failed to write to file logs, exception: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        try{
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to close logs file, exception: " + e.getMessage());
        }
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

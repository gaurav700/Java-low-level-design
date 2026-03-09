package com.pm;

import com.pm.enums.LogLevel;
import com.pm.strategy.impl.ColorConsoleFormatter;
import com.pm.strategy.impl.ConsoleLogAppender;
import com.pm.strategy.impl.FileLogAppender;

        public class Main {

            public static void main(String[] args) {

                LogManager logManager = LogManager.getInstance();

                // configure root logger
                Logger rootLogger = logManager.getRootLogger();
                rootLogger.setLevel(LogLevel.DEBUG);

                ConsoleLogAppender consoleAppender = new ConsoleLogAppender();
                consoleAppender.setFormatter(new ColorConsoleFormatter());

                rootLogger.addAppender(consoleAppender);
                rootLogger.addAppender(new FileLogAppender("app.log"));

                // get logger
                Logger logger = logManager.getLogger("Main");

                // log messages
                logger.debug("Debug message");
                logger.info("Application started");
                logger.warn("Low memory warning");
                logger.error("Something went wrong");
                logger.fatal("System crash");

                // shutdown logger
                logManager.shutdown();
            }
        }
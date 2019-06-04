package com.agentapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerLog {

    private static Logger logger = LoggerFactory.getLogger(ServerLog.class);

    public static void infoLog(String s) {
        logger.trace(s);
    }

    public static void errorLog(String s) {
        logger.error(s);
    }

    public static void warnLog(String s) {
        logger.warn(s);
    }
}

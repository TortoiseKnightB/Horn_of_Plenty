package com.knight.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author T-Knight
 * @create 2021-08-27 14:05
 */
public class Util {
    private static final Logger log = LoggerFactory.getLogger(Util.class);

    public static void loginfo() {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }
}
package com.knight.test;

import com.knight.test.util.Util;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(TestApplication.class);

    private static void loginfo() {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }

    @Test
    void contextLoads() {
        loginfo();
        Util.loginfo();
    }

}

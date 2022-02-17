package com.knight.logback;

import com.knight.logback.util.PlusTest;
import com.knight.logback.util.Util;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("测试基础日志功能")
    @Test
    void loggerTest() {
        loginfo();
        Util.loginfo();
    }

    @DisplayName("测试增强日志功能")
    @Test
    void loggerPlusTest() {
        PlusTest.loggerPlusInfo();
    }

}

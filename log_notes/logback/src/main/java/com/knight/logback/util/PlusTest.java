package com.knight.logback.util;

import com.knight.logback.loggerplus.LogData;
import com.knight.logback.loggerplus.LoggerPlus;
import com.knight.logback.loggerplus.LoggerPlusFactory;

/**
 * 功能增强日志测试
 *
 * @author TortoiseKnightB
 * @date 2022/02/17
 */
public class PlusTest {

    private static final LoggerPlus loggerPlus = LoggerPlusFactory.getLoggerPlus(PlusTest.class);

    /**
     * 日志打印基础功能
     */
    public static void loggerPlusInfo() {
        LogData logData = LogData.builder()
                .url("url")
                .params("params")
                .result("result")
                .elapsedTime(200L)
                .success(true)
                .serverIp("serverIp")
                .clientIp("clientIp")
                .message("message")
                .build();
        loggerPlus.info(logData);
    }

    /**
     * 日志打印扩展功能
     */
    public static void loggerPlusInfoExtension() {

        LogData logData1 = LogData.builder()
                .url("url")
                .params("params")
                .result("result")
                .elapsedTime(200L)
                .success(true)
                .serverIp("serverIp")
                .clientIp("clientIp")
                .message("message")
                .build();
        loggerPlus.info(logData1);


        LogData logData2 = LogData.builder()
                .url("url")
                .params("params")
                .result("result")
                .elapsedTime(200L)
                .success(true)
                .serverIp("serverIp")
                .clientIp("clientIp")
                .build();
        loggerPlus.info(logData2);


        try {
            int a = 10 / 0;
        } catch (Throwable e) {
            LogData logData3 = LogData.builder()
                    .url("url")
                    .params("params")
                    .result("result")
                    .elapsedTime(200L)
                    .success(true)
                    .serverIp("serverIp")
                    .clientIp("clientIp")
                    .message("message")
                    .exception(e)
                    .build();
            loggerPlus.error(logData3);
        }


        LogData logData4 = LogData.builder()
                .url("url")
                .params("params")
                .result("result")
                .elapsedTime(200L)
                .success(true)
                .serverIp("serverIp")
                .clientIp("clientIp")
                .message("message")
                .build();
        loggerPlus.error(logData4);


        LogData logData5 = LogData.builder()
                .url("url")
                .params("params")
                .result("result")
                .elapsedTime(200L)
                .success(true)
                .serverIp("serverIp")
                .clientIp("clientIp")
                .build();
        loggerPlus.error(logData5);
    }
}

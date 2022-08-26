package com.knight.web.utils.logger;


import org.slf4j.LoggerFactory;

/**
 * 自定义日志工厂类
 *
 * @author TortoiseKnightB
 * @date 2022/08/25
 * @see LogData
 * @see LoggerHelper
 */
public class LoggerPlusFactory {

    private LoggerPlusFactory() {
    }

    public static LoggerHelper getLogger() {
        return getLogger(LoggerHelper.class);
    }

    public static LoggerHelper getLogger(Class<?> clazz) {
        return new LoggerHelper(LoggerFactory.getLogger(clazz));
    }
}

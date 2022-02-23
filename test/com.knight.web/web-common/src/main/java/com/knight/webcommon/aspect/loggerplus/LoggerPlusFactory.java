package com.knight.webcommon.aspect.loggerplus;

import org.slf4j.LoggerFactory;

/**
 * 自定义功能增强日志工厂
 *
 * @author TortoiseKnightB
 * @date 2022/02/23
 */
public class LoggerPlusFactory {

    public static LoggerPlus getLoggerPlus(Class<?> clazz) {
        LoggerPlus loggerPlus = new LoggerPlusImpl(LoggerFactory.getLogger(clazz));
        LoggerPlusProxy loggerPlusProxy = new LoggerPlusProxy(loggerPlus);
        return loggerPlusProxy.create();
    }
}


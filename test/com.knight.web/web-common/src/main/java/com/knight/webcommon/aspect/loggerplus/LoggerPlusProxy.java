package com.knight.webcommon.aspect.loggerplus;

import java.lang.reflect.Proxy;

/**
 * 自定义功能增强日志代理
 *
 * @author TortoiseKnightB
 * @date 2022/02/23
 */
public class LoggerPlusProxy {

    private LoggerPlus loggerPlus;

    public LoggerPlusProxy(LoggerPlus loggerPlus) {
        this.loggerPlus = loggerPlus;
    }

    public LoggerPlus create() {
        ClassLoader classLoader = LoggerPlus.class.getClassLoader();
        Class<?>[] interfaces = LoggerPlusImpl.class.getInterfaces();
        LoggerPlusHandler loggerPlusHandler = new LoggerPlusHandler(loggerPlus);
        return (LoggerPlus) Proxy.newProxyInstance(classLoader, interfaces, loggerPlusHandler);
    }
}

package com.knight.logback.loggerplus;

import com.knight.logback.consts.LoggerPlusConstants;
import org.slf4j.MDC;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义功能增强日志处理类
 * <p>
 * 在{@link #invoke(Object, Method, Object[]) invoke}方法中实现自定义功能增强
 *
 * @author TortoiseKnightB
 * @date 2022/02/16
 */
public class LoggerPlusHandler implements InvocationHandler {

    private final LoggerPlus loggerPlus;

    public LoggerPlusHandler(LoggerPlus loggerPlus) {
        this.loggerPlus = loggerPlus;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        LoggerPlusAnnotation annotation = method.getAnnotation(LoggerPlusAnnotation.class);
        LogData logData = (LogData) args[0];

        if (annotation != null) {
            // MDC在日志中的输出格式：%X{url}
            MDC.put(LoggerPlusConstants.URL, logData.getUrl());
            MDC.put(LoggerPlusConstants.PARAMS, logData.getParams());
            MDC.put(LoggerPlusConstants.RESULT, logData.getResult());
            MDC.put(LoggerPlusConstants.ELAPSED_TIME, String.valueOf(logData.getElapsedTime()));
            MDC.put(LoggerPlusConstants.SUCCESS, String.valueOf(logData.getSuccess()));
            MDC.put(LoggerPlusConstants.SERVER_IP, logData.getServerIp());
            MDC.put(LoggerPlusConstants.CLIENT_IP, logData.getClientIp());
            MDC.put(LoggerPlusConstants.HTTP_METHOD, logData.getHttpMethod());
            MDC.put(LoggerPlusConstants.CLASS_METHOD, logData.getClassMethod());
        }

        Object result = method.invoke(loggerPlus, args);

        if (annotation != null) {
            MDC.remove(LoggerPlusConstants.URL);
            MDC.remove(LoggerPlusConstants.PARAMS);
            MDC.remove(LoggerPlusConstants.RESULT);
            MDC.remove(LoggerPlusConstants.ELAPSED_TIME);
            MDC.remove(LoggerPlusConstants.SUCCESS);
            MDC.remove(LoggerPlusConstants.SERVER_IP);
            MDC.remove(LoggerPlusConstants.CLIENT_IP);
            MDC.remove(LoggerPlusConstants.HTTP_METHOD);
            MDC.remove(LoggerPlusConstants.CLASS_METHOD);
        }

        return result;
    }
}

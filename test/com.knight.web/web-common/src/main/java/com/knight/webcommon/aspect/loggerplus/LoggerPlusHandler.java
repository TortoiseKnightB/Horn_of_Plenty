package com.knight.webcommon.aspect.loggerplus;

import com.knight.webcommon.consts.LoggerPlusConstants;
import com.knight.webcommon.model.entity.LogData;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义功能增强日志处理类
 *
 * @author TortoiseKnightB
 * @date 2022/02/23
 */
public class LoggerPlusHandler implements InvocationHandler {

    private final LoggerPlus loggerPlus;

    public LoggerPlusHandler(LoggerPlus loggerPlus) {
        this.loggerPlus = loggerPlus;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        LoggerPlusAnnotation annotation = method.getAnnotation(LoggerPlusAnnotation.class);
        LogData logData;

        if (annotation != null) {
            logData = (LogData) args[0];
            MDC.put(LoggerPlusConstants.URL, logData.getUrl());
            MDC.put(LoggerPlusConstants.PARAMS, logData.getParams());
            MDC.put(LoggerPlusConstants.RESULT, logData.getResult());
            MDC.put(LoggerPlusConstants.ELAPSED_TIME, String.valueOf(logData.getElapsedTime()));
            MDC.put(LoggerPlusConstants.SUCCESS, String.valueOf(logData.getSuccess()));
            MDC.put(LoggerPlusConstants.SERVER_IP, logData.getServerIp());
            MDC.put(LoggerPlusConstants.CLIENT_IP, logData.getClientIp());
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
        }

        return result;
    }
}


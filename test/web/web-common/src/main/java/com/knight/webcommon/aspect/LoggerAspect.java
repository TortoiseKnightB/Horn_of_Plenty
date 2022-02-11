package com.knight.webcommon.aspect;

import cn.hutool.json.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knight.gatewaycommon.model.response.ResultInfo;
import com.knight.gatewaycommon.utils.JsonHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局日志记录
 *
 * @author TortoiseKnightB
 * @date 2022/02/10
 */
@Aspect
@Order(2)
@Component
public class LoggerAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("@annotation(com.knight.webcommon.aspect.annotation.LoggerAnnotation)")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object service(ProceedingJoinPoint pjp) {
        System.out.println("LoggerAspect start");

        long startTime = System.currentTimeMillis();
        long elapsedTime;

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        Object result;
        Object[] args = pjp.getArgs();
        try {
            result = pjp.proceed(args);

            elapsedTime = System.currentTimeMillis() - startTime;
            // TODO
            logger.info("url: " + request.getRequestURI() + ", params: " + JsonHelper.toJSON(args) + ", result: " + JsonHelper.toJSON(result) + ", elapsedTime: " + elapsedTime);

        } catch (Throwable e) {
            String message = "日志错误";
            result = new ResultInfo<>().fail(message);

            elapsedTime = System.currentTimeMillis() - startTime;
            // TODO
            logger.info("url: " + request.getRequestURI() + ", params: " + JsonHelper.toJSON(args) + ", result: " + JsonHelper.toJSON(result) + ", elapsedTime: " + elapsedTime);
        }

        System.out.println("LoggerAspect end");
        return result;
    }
}

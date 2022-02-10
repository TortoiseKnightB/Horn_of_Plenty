package com.knight.webcommon.aspect;

import com.knight.gatewaycommon.model.response.ResultInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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

        Object result;
        Object[] args = pjp.getArgs();

        try {
            result = pjp.proceed(args);

            elapsedTime = System.currentTimeMillis() - startTime;
            // TODO
            logger.info("url: " + ", params: " + args.toString() + ", result: " + result.toString() + ", elapsedTime: " + String.valueOf(elapsedTime));

        } catch (Throwable e) {
            String message = "日志错误";
            result = new ResultInfo<>().fail(message);

            elapsedTime = System.currentTimeMillis() - startTime;
            // TODO
            logger.error("url: " + ", params: " + args.toString() + ", result: " + result.toString() + ", elapsedTime: " + String.valueOf(elapsedTime));
        }

        System.out.println("LoggerAspect end");
        return result;
    }
}

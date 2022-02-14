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
 * 全局异常处理、日志记录 AOP
 *
 * @author TortoiseKnightB
 * @date 2022/02/14
 */
@Aspect
@Order(1)
@Component
public class GlobalHandlerAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    // TODO 暂时借助 GlobalHandlerAnnotation 测验
    @Pointcut("@annotation(com.knight.webcommon.aspect.annotation.GlobalHandlerAnnotation)")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object service(ProceedingJoinPoint pjp) {
        logger.info("GlobalHandlerAspect start");

        Object result;
        Object[] args = pjp.getArgs();
        try {
            logger.info("GlobalHandlerAspect proceed");
            result = pjp.proceed(args);
        } catch (Throwable e) {
            logger.error("GlobalHandlerAspect exception");
            String message = "系统错误";
            result = new ResultInfo<>().fail(message);
        }

        logger.info("GlobalHandlerAspect end");
        return result;
    }


}

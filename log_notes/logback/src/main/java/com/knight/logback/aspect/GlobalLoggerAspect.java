package com.knight.logback.aspect;

import com.knight.logback.loggerplus.LogData;
import com.knight.logback.loggerplus.LoggerPlus;
import com.knight.logback.loggerplus.LoggerPlusFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局日志记录，MDC的使用放在了{@link com.knight.logback.loggerplus.LoggerPlusHandler LoggerPlusHandler}
 *
 * @author TortoiseKnightB
 * @date 2022/08/23
 */
@Aspect
@Component
public class GlobalLoggerAspect {

    private static LoggerPlus loggerPlus = LoggerPlusFactory.getLoggerPlus(GlobalLoggerAspect.class);

    @Pointcut("execution(public * com.knight.logback.controller..*.*(..))")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object service(ProceedingJoinPoint pjp) {

        long startTime = System.currentTimeMillis();
        Object result = null;

        Object[] args = pjp.getArgs();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 这个好像没啥用，不如直接用MDC
        LogData logData = LogData.builder()
                .url(request.getRequestURL().toString())
                .params(args.toString())
                .serverIp("serverIp")
                .clientIp(request.getRemoteAddr())
                .classMethod(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName())
                .httpMethod(request.getMethod())
                .build();

        try {
            result = pjp.proceed(args);

            logData.setSuccess(true);
            logData.setResult(result.toString());
            logData.setElapsedTime(System.currentTimeMillis() - startTime);
            loggerPlus.info(logData);
        } catch (Throwable e) {
            logData.setSuccess(false);
            logData.setException(e);
            loggerPlus.error(logData);
        }

        return result;
    }


}

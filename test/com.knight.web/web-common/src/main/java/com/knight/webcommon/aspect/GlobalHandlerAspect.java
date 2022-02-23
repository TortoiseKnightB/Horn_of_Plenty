package com.knight.webcommon.aspect;

import com.knight.gatewaycommon.model.response.ResultInfo;
import com.knight.gatewaycommon.utils.JsonHelper;
import com.knight.webcommon.aspect.loggerplus.LoggerPlus;
import com.knight.webcommon.aspect.loggerplus.LoggerPlusFactory;
import com.knight.webcommon.model.entity.LogData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


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

    private static LoggerPlus logger = LoggerPlusFactory.getLoggerPlus(GlobalHandlerAspect.class);

//    @Pointcut("@annotation(com.knight.webcommon.aspect.annotation.GlobalHandlerAnnotation)")
    @Pointcut("execution(public * com.knight.webgateway.controller..*.*(..))")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object service(ProceedingJoinPoint pjp) {

        long startTime = System.currentTimeMillis();
        long elapsedTime;
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        ResultInfo result = null;
        Object[] args = pjp.getArgs();
        try {
            result = (ResultInfo) pjp.proceed(args);
            elapsedTime = System.currentTimeMillis() - startTime;
            logger.info(LogData.builder()
                    .url(request.getRequestURI())
                    .params(JsonHelper.toJSON(args))
                    .result(JsonHelper.toJSON(result))
                    .elapsedTime(elapsedTime)
                    .success(result.getSuccess())
                    .serverIp(request.getRequestURI())
                    .clientIp("locolhost")
                    .message("GlobalHandlerAspect proceed")
                    .build());
        } catch (Throwable e) {
            String message = "系统错误";
            elapsedTime = System.currentTimeMillis() - startTime;
            logger.info(LogData.builder()
                    .url(request.getRequestURI())
                    .params(JsonHelper.toJSON(args))
                    .result(JsonHelper.toJSON(result))
                    .elapsedTime(elapsedTime)
                    .success(false)
                    .serverIp(request.getRequestURI())
                    .clientIp("locolhost")
                    .message(message)
                    .build());
            result = new ResultInfo<>().fail(message);
        }

        return result;
    }


}

package com.knight.webcommon.aspect;

import com.knight.gatewaycommon.model.response.ResultInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 全局异常统一处理
 *
 * @author TortoiseKnightB
 * @date 2022/02/08
 */
@Aspect
@Order(1)
@Component
public class GlobalExceptionHandlerAspect {

    // TODO 待日志功能完成后，与日志合并成，并改成全局生效
    @Pointcut("@annotation(com.knight.webcommon.aspect.annotation.ExceptionHandlerAnnotation)")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object service(ProceedingJoinPoint pjp) {
        System.out.println("GlobalExceptionHandlerAspect start");
        Object result;
        Object[] args = pjp.getArgs();
        try {
            System.out.println("GlobalExceptionHandlerAspect proceed");
            result = pjp.proceed(args);
        } catch (Throwable e) {
            System.out.println("GlobalExceptionHandlerAspect exception");
            String message = "系统错误";
            result = new ResultInfo<>().fail(message);
        }
        System.out.println("GlobalExceptionHandlerAspect end");
        return result;
    }

}

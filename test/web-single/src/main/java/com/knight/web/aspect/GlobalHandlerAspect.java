package com.knight.web.aspect;

import com.knight.web.exception.CommonException;
import com.knight.web.model.response.ResultInfo;
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
 * 全局处理AOP：异常处理、日志记录
 *
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Aspect
@Order(1)
@Component
public class GlobalHandlerAspect {


    @Pointcut("execution(public * com.knight.web.controller..*.*(..))")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object service(ProceedingJoinPoint pjp) {

        long startTime = System.currentTimeMillis();

        ResultInfo result;
        Object[] args = pjp.getArgs();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        try {
            result = (ResultInfo) pjp.proceed(args);


        } catch (CommonException e) {
            result = new ResultInfo<>().fail(e.getErrorCode(), e.getMessage());

        } catch (Throwable e) {
            result = new ResultInfo<>().fail(e.getMessage());

        }
        return result;
    }
}

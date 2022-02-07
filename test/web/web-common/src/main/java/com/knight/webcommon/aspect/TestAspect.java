package com.knight.webcommon.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author TortoiseKnightB
 * @date 2022/02/07
 */
@Aspect
@Order(3)
@Component
public class TestAspect {

    // 切入点
    @Pointcut("@annotation(com.knight.webcommon.aspect.annotation.TestAnnotation)")
//    @Pointcut("execution(public * com.knight.webgateway.controller..*.*(..) )")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("@@@ test aspect before");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("@@@ test aspect after");
    }
}

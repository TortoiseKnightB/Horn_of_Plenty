package com.knight.webcommon.aspect;

import com.sun.istack.internal.NotNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * 测试注解 AOP
 *
 * @author TortoiseKnightB
 * @date 2022/02/07
 */
@Aspect
@Order(5)
@Component
public class TestAspect {

    // 切入点
    @Pointcut("@annotation(com.knight.webcommon.aspect.annotation.TestAnnotation)")
//    @Pointcut("execution(public * com.knight.webgateway.controller..*.*(..) )")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("@@@ test aspect before");
        // 切入点信息
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        // 获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());
    }

    @After("pointcut()")
    public void after() {
        System.out.println("@@@ test aspect after");
    }
}

package com.knight.webcommon.aspect.annotation;

import java.lang.annotation.*;

/**
 * 注解作用：在方法上使用，对该方法进行日志记录
 *
 * @author TortoiseKnightB
 * @date 2022/02/10
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerAnnotation {
}
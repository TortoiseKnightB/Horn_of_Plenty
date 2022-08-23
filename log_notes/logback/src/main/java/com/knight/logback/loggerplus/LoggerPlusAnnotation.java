package com.knight.logback.loggerplus;

import java.lang.annotation.*;

/**
 * 注解作用：增强日志功能
 *
 * @author TortoiseKnightB
 * @date 2022/02/16
 * @see LoggerPlusHandler
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerPlusAnnotation {
}

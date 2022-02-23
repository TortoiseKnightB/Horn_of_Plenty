package com.knight.webcommon.aspect.loggerplus;

import java.lang.annotation.*;

/**
 * 注解作用：在自定义的日志方法上使用,增强日志功能
 *
 * @author TortoiseKnightB
 * @date 2022/02/23
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerPlusAnnotation {
}

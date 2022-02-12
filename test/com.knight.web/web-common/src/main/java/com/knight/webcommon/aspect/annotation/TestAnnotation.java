package com.knight.webcommon.aspect.annotation;

import java.lang.annotation.*;

/**
 * 测试注解
 *
 * @author TortoiseKnightB
 * @date 2022/02/07
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
}

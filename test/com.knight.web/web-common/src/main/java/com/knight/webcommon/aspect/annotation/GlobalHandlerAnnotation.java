package com.knight.webcommon.aspect.annotation;

import java.lang.annotation.*;

/**
 * @author TortoiseKnightB
 * @date 2022/02/14
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalHandlerAnnotation {
}

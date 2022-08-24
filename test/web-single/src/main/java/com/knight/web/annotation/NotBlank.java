package com.knight.web.annotation;

import org.springframework.core.annotation.AliasFor;
import com.knight.web.aspect.NullCheckAspect;

import java.lang.annotation.*;

/**
 * 非空校验注解，在入参中需要非空校验的字段上标注
 *
 * @author TortoiseKnightB
 * @date 2022/08/24
 * @see NullCheckAspect
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NotBlank {

    @AliasFor("message")
    String value() default "";

    /**
     * 非空校验提示信息
     *
     * @return
     */
    @AliasFor("value")
    String message() default "";
}


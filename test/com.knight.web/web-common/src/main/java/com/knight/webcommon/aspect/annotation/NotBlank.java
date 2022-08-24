package com.knight.webcommon.aspect.annotation;

import com.knight.webcommon.aspect.NotNullAspect;

import java.lang.annotation.*;

/**
 * 注解作用：检验字段值非空
 * <p>
 * 实现方式：在 NotNullAspect 类中实现，需要搭配 {@link NotNullAnnotation @NotNullAnnotation}
 *
 * @author TortoiseKnightB
 * @date 2022/02/08
 * @see NotNullAnnotation
 * @see NonBlank
 * @see NotNullAspect
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NotBlank {

    /**
     * 提示信息
     *
     * @return
     */
    String message() default "参数不能为空";
}

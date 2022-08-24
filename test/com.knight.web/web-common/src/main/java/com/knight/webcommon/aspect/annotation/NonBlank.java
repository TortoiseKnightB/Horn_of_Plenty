package com.knight.webcommon.aspect.annotation;

import org.springframework.core.annotation.AliasFor;
import com.knight.webcommon.aspect.NullCheckAspect;

import java.lang.annotation.*;

/**
 * 简单非空校验注解，在入参中需要非空校验的字段上标注，在{@link NullCheckAspect}中实现校验逻辑
 * <p>
 * 设定规范，前端传过来的参数都被封装进一个参数实体，只检查第一层参数非空及父类参数非空。简单易用
 * <p>
 * 如无特殊需求，建议优先考虑该注解
 *
 * @author TortoiseKnightB
 * @date 2022/08/24
 * @see NotBlank
 * @see NullCheckAspect
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NonBlank {

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

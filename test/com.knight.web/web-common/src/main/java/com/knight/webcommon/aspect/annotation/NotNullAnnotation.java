package com.knight.webcommon.aspect.annotation;

import com.knight.webcommon.aspect.NotNullAspect;

import java.lang.annotation.*;

/**
 * 注解作用：在方法上使用，检验方法入参非空；在复杂类型字段上使用，校验复杂类型非空（参考：testAspect、SchoolParam）
 * <p>
 * 实现方式：在 NotNullAspect 类中实现，搭配 @RequestBody 使用。@RequestBody 限制了传过来的参数为 json 字符串，并自动解析赋值
 *
 * @author TortoiseKnightB
 * @date 2022/02/08
 * @see NotBlank
 * @see NotNullAspect
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullAnnotation {
}

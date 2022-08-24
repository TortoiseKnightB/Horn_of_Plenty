package com.knight.webcommon.aspect;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.knight.gatewaycommon.exception.CommonException;
import com.knight.gatewaycommon.model.enums.EnumCommonException;
import com.knight.webcommon.aspect.annotation.NonBlank;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 请求参数非空校验AOP，对入参中标注{@link NonBlank @NonBlank}的字段进行非空校验
 *
 * @author TortoiseKnightB
 * @date 2022/08/24
 * @see NonBlank
 */
@Aspect
@Order(3)
@Component
public class NullCheckAspect {

    @Pointcut("execution(public * com.knight.webgateway.controller..*.*(..))")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void paramValidate(JoinPoint point) {
        Object[] params = point.getArgs();
        // 方法没有参数，跳过校验
        if (params.length == 0) {
            return;
        }
        // 获取该类及父类中所有字段进行校验
        Class<?> clazz = params[0].getClass();
        for (Field field : ReflectUtil.getFields(clazz)) {
            NonBlank annotation = AnnotationUtils.getAnnotation(field, NonBlank.class);
            if (annotation != null) {
                validate(field, params[0], annotation.message());
            }
        }
    }


    /**
     * 校验字段非空
     *
     * @param field   被注解修饰的字段
     * @param param   当前参数对象
     * @param message 非空校验提示信息
     */
    private void validate(Field field, Object param, String message) {
        Object value = ReflectUtil.getFieldValue(param, field);
        if (value == null || (value instanceof String && StrUtil.isBlank((String) value))) {
            message = StrUtil.isBlank(message) ? field.getDeclaringClass().getTypeName() + "." + field.getName() + "不能为空" : message;
            throw new CommonException(EnumCommonException.REQUEST_PARAM_IS_NULL, message);
        }
    }
}

package com.knight.webcommon.aspect;

import com.knight.webcommon.aspect.annotation.NotBlank;
import com.knight.webcommon.aspect.annotation.NotNullAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * NotNullAnnotation 注解 AOP
 *
 * @author TortoiseKnightB
 * @date 2022/02/08
 */
@Aspect
@Order(3)
@Component
public class NotNullAspect {

    @Pointcut("@annotation(com.knight.webcommon.aspect.annotation.NotNullAnnotation)")
    private void pointcut() {
    }

    /**
     * 校验参数非空
     * <p>
     * 注解 @RequestBody 已经将 json 字符串解析赋值
     *
     * @param joinPoint
     */
    @Before("pointcut()")
    public void checkParam(JoinPoint joinPoint) throws IllegalAccessException {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg == null) {
                // TODO 抛出空异常
                System.out.println("传入参数为空");
                break;
            }
            if (arg instanceof CharSequence) {
                // 若为字符串类型，检查是否为空
                if (0 == ((CharSequence) arg).length()) {
                    // TODO 抛出空异常
                    System.out.println("传入参数为空字符串");
                    break;
                }
            } else {
                // 非字符串类型，校验该类及其父类的各字段
                Field[] declaredFields;
                for (Class<?> argClass = arg.getClass(); argClass != null; argClass = argClass.getSuperclass()) {
                    declaredFields = argClass.getDeclaredFields();
                    for (Field field : declaredFields) {
                        // 该字段不能为空
                        NotBlank notBlank = field.getAnnotation(NotBlank.class);
                        if (notBlank != null) {
                            // 如果 accessible 标志被设置为true，那么反射对象在使用的时候，不会去检查Java语言权限控制（private之类的）
                            field.setAccessible(true);
                            validteNotBlank(field, arg, notBlank);
                        }
                    }
                }
            }
        }
    }

    /**
     * 验证字段值不为空
     *
     * @param field      类中的字段属性
     * @param arg        实例类
     * @param annotation 对应注解
     */
    private void validteNotBlank(Field field, Object arg, NotBlank annotation) throws IllegalAccessException {
        Object o = field.get(arg);
        if (o == null) {
            // TODO 抛出空异常
            System.out.println("参数为空[" + annotation.message() + "]");
        } else if (o instanceof CharSequence) {
            if (0 == ((CharSequence) o).length()) {
                // TODO 抛出空异常
                System.out.println("参数为空字符串[" + annotation.message() + "]");
            }
        } else if (field.getAnnotation(NotNullAnnotation.class) != null) {
            // 如果字段为复杂类（需要有 @NotNullAnnotation 注解），递归校验
            Class<?> oClass = o.getClass();
            Field[] fields = oClass.getDeclaredFields();
            for (Field f : fields) {
                NotBlank anno = f.getAnnotation(NotBlank.class);
                f.setAccessible(true);
                validteNotBlank(f, o, anno);
            }
        }
    }
}

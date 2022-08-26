package com.knight.web.aspect;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import com.knight.web.consts.LogConstant;
import com.knight.web.exception.CommonException;
import com.knight.web.model.response.ResultInfo;
import com.knight.web.utils.JsonHelper;
import com.knight.web.utils.logger.LogData;
import com.knight.web.utils.logger.LoggerHelper;
import com.knight.web.utils.logger.LoggerPlusFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局处理AOP：异常处理、日志记录
 *
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Aspect
@Order(1)
@Component
public class GlobalHandlerAspect {

    LoggerHelper logger = LoggerPlusFactory.getLogger();


    @Pointcut("execution(public * com.knight.web.controller..*.*(..))")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object service(ProceedingJoinPoint pjp) {

        long startTime = System.currentTimeMillis();

        ResultInfo result;
        Object[] args = pjp.getArgs();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        MDC.put(LogConstant.HTTP_METHOD, request.getMethod());
        MDC.put(LogConstant.SERVER_IP, "serverIp");
        MDC.put(LogConstant.CLIENT_IP, request.getRemoteAddr());
        MDC.put(LogConstant.URL, request.getRequestURI());
        MDC.put(LogConstant.CLASS_METHOD, StrUtil.format("{}.{}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName()));
        MDC.put(LogConstant.PARAMS, JsonHelper.toJSON(args));

        try {
            result = (ResultInfo) pjp.proceed(args);
            logger.info(LogData.builder()
                    .success(result.getSuccess())
                    .result(JsonHelper.toJSON(result))
                    .elapsedTime(System.currentTimeMillis() - startTime)
                    .build());
        } catch (CommonException e) {
            result = new ResultInfo<>().fail(e.getErrorCode(), e.getMessage());
            logger.error(LogData.builder()
                    .success(result.getSuccess())
                    .result(JsonHelper.toJSON(result))
                    .message(e.getMessage())
                    .codeMessage(e.getCodeMessage())
                    .exception(e)
                    .elapsedTime(System.currentTimeMillis() - startTime)
                    .build());
        } catch (Throwable e) {
            result = new ResultInfo<>().fail(e.getMessage());
            logger.error(LogData.builder()
                    .success(result.getSuccess())
                    .result(JsonHelper.toJSON(result))
                    .message(e.getMessage())
                    .exception(e)
                    .elapsedTime(System.currentTimeMillis() - startTime)
                    .build());
        } finally {
            MDC.clear();
        }
        return result;
    }
}

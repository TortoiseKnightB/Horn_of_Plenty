package com.knight.logback.loggerplus;

import org.slf4j.Logger;

/**
 * 自定义功能增强版日志
 * <p>
 * 这里采用继承 Logger 接口，而不是直接继承 logback 的实现类，是为了不把底层日志系统固定为 logback
 *
 * @author TortoiseKnightB
 * @date 2022/02/16
 */
public interface LoggerPlus extends Logger {

    @LoggerPlusAnnotation
    void info(LogData logData);

    @LoggerPlusAnnotation
    void error(LogData logData);
}

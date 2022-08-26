package com.knight.web.utils.logger;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;

/**
 * 自定义日志工具
 *
 * @author TortoiseKnightB
 * @date 2022/08/25
 * @see LogData
 * @see LoggerPlusFactory
 */
public class LoggerHelper {

    private Logger logger;

    public LoggerHelper(Logger logger) {
        this.logger = logger;
    }


    public void info(String msg) {
        logger.info(StrUtil.format("-{}", msg));
    }

    public void info(LogData logData) {
        logger.info(commonData(logData));
    }

    public void error(String msg) {
        logger.error(StrUtil.format("-{}", msg));
    }

    public void error(LogData logData) {
        logger.error(commonData(logData));
    }

    private String commonData(LogData logData) {
        StringBuilder str = new StringBuilder();
        if (logData == null) {
            return str.toString();
        }
        if (logData.getHttpMethod() != null) {
            str.append(" HTTP_METHOD: ").append(logData.getHttpMethod()).append("\n");
        }
        if (logData.getServerIp() != null) {
            str.append(" SERVER_IP: ").append(logData.getServerIp()).append("\n");

        }
        if (logData.getClientIp() != null) {
            str.append(" CLIENT_IP: ").append(logData.getClientIp()).append("\n");

        }
        if (logData.getUrl() != null) {
            str.append(" URL: ").append(logData.getUrl()).append("\n");

        }
        if (logData.getClassMethod() != null) {
            str.append(" CLASS_METHOD: ").append(logData.getClassMethod()).append("\n");

        }
        if (logData.getSuccess() != null) {
            str.append(" SUCCESS: ").append(logData.getSuccess()).append("\n");

        }
        if (logData.getParams() != null) {
            str.append(" PARAMS: ").append(logData.getParams()).append("\n");

        }
        if (logData.getResult() != null) {
            str.append(" RESULT: ").append(logData.getResult()).append("\n");

        }
        if (logData.getElapsedTime() != null) {
            str.append(" ELAPSED_TIME: ").append(logData.getElapsedTime()).append("\n");

        }
        if (logData.getMessage() != null) {
            str.append(" MESSAGE: ").append(logData.getMessage()).append("\n");

        }
        if (logData.getCodeMessage() != null) {
            str.append(" CODE_MESSAGE: ").append(logData.getCodeMessage()).append("\n");

        }
        if (logData.getException() != null) {
            str.append(" EXCEPTION: ").append(logData.getException()).append("\n");
        }
        return str.substring(0, str.length() - 1);
    }


}

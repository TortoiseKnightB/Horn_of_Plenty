package com.knight.web.utils.logger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志信息实体
 *
 * @author TortoiseKnightB
 * @date 2022/08/25
 * @see LoggerHelper
 * @see LoggerPlusFactory
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogData {

    private String httpMethod;

    private String serverIp;

    private String clientIp;

    private String url;

    private String classMethod;

    private Boolean success;

    private String params;

    private String result;

    private Long elapsedTime;

    private String message;

    private String codeMessage;

    private Throwable exception;

}
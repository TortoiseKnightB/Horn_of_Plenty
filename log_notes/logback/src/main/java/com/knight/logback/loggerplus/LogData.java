package com.knight.logback.loggerplus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TortoiseKnightB
 * @date 2022/02/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogData {

    private String url;

    private String params;

    private String result;

    private Long elapsedTime;

    private Boolean success;

    private String serverIp;

    private String clientIp;

    private String message;

    private Throwable exception;

    private String httpMethod;

    private String classMethod;

}

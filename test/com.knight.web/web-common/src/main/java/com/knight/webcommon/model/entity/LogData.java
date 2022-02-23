package com.knight.webcommon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志内容实体
 *
 * @author TortoiseKnightB
 * @date 2022/02/23
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

}

package com.knight.gatewaycommon.model.enums;

import lombok.Getter;

/**
 * 公共异常枚举类
 * <p>
 * 基础异常从1000开始
 * <p>
 * 数据库异常从2000开始
 *
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Getter
public enum EnumCommonException {

    /**
     * 成功
     */
    SUCCESS(200, "成功", "成功"),
    /**
     * 网络不给力，服务器内部错误
     */
    SYSTEM_INTERNAL_ANOMALY(1099, "网络不给力，请稍后重试", "服务器内部错误"),
    /**
     * 请求参数为空
     */
    REQUEST_PARAM_IS_NULL(1001, "网络不给力，请稍后重试", "请求参数含空"),
    /**
     * 数据库结果异常
     */
    DB_RESULT_ERROR(2001, "网络不给力，请稍后重试", "数据库结果异常");

    /**
     * 错误代码
     */
    private final Integer errorCode;
    /**
     * 传递给前端的错误信息
     */
    private final String message;
    /**
     * 错误原因
     */
    private final String codeMessage;

    EnumCommonException(Integer errorCode, String message, String codeMessage) {
        this.errorCode = errorCode;
        this.message = message;
        this.codeMessage = codeMessage;
    }

    public EnumCommonException getEnum(int errorCode) {
        for (EnumCommonException type : EnumCommonException.values()) {
            if (type.getErrorCode() == errorCode) {
                return type;
            }
        }
        return null;
    }
}

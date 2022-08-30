package com.knight.web.exception;

import com.knight.web.model.enums.EnumCommonException;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础异常类
 *
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Getter
@Setter
public class CommonException extends RuntimeException {

    /**
     * 错误代码
     */
    private Integer errorCode;
    /**
     * 传递给前端的错误信息
     */
    private String message;
    /**
     * 具体错误原因
     */
    private String codeMessage;

    public CommonException() {
    }

    public CommonException(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public CommonException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public CommonException(EnumCommonException exception) {
        super(exception.getCodeMessage());
        this.errorCode = exception.getErrorCode();
        this.message = exception.getMessage();
        this.codeMessage = exception.getCodeMessage();
    }

    /**
     * @param exception 公共异常枚举类
     * @param message   传递给前端的错误信息
     */
    public CommonException(EnumCommonException exception, String message) {
        super(exception.getCodeMessage());
        this.errorCode = exception.getErrorCode();
        this.message = message;
        this.codeMessage = exception.getCodeMessage();
    }

    /**
     * @param exception   公共异常枚举类
     * @param message     传递给前端的错误信息(传null返回默认值)
     * @param codeMessage 具体错误原因（传null返回默认值）
     */
    public CommonException(EnumCommonException exception, String message, String codeMessage) {
        super(exception.getCodeMessage());
        this.errorCode = exception.getErrorCode();
        this.message = message == null ? exception.getMessage() : message;
        this.codeMessage = codeMessage == null ? exception.getCodeMessage() : codeMessage;
    }

    @Override
    public String toString() {
        return "CommonException{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                ", codeMessage='" + codeMessage + '\'' +
                '}';
    }
}

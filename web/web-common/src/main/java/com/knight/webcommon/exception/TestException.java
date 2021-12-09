package com.knight.webcommon.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TestException extends RuntimeException {

    /**
     * 错误代码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String message;



}

package com.knight.webcommon.model.response;

import lombok.Data;

/**
 * 中台 api 接口返回结果封装类
 * @author TortoiseKnightB
 * @date 2021/12/24
 */
// TODO 这部分和网关有重叠，以后可以提取出来
@Data
public class ResultInfo<T> {

    private T data;

    private Boolean success;

    private Integer code;

    private String message;

    /**
     * 成功返回结果
     *
     * @param data 成功返回的数据
     * @return
     */
    public ResultInfo<T> succeed(T data) {
        this.data = data;
        this.success = true;
        // TODO 这里 code 以后用 枚举类 实现
        this.code = 0;
        return this;
    }

    /**
     * 根据错误信息返回结果
     *
     * @param message 错误信息
     * @return
     */
    public ResultInfo<T> fail(String message) {
        this.success = false;
        // TODO 这里为默认错误 code
        this.code = 1000;
        this.message = message;
        return this;
    }

    // TODO 还有其他很多返回情况，比如根据 code 返回结果
}
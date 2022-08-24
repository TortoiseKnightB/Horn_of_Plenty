package com.knight.gatewaycommon.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.knight.gatewaycommon.model.enums.EnumCommonException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口返回结果封装类
 *
 * @author TortoiseKnightB
 * @date 2021/12/24
 */
@ApiModel(description = "网关接口请求结果包装类")
@Data
public class ResultInfo<T> {

    @ApiModelProperty("请求结果数据")
    @JsonProperty("Data")
    private T data;

    @ApiModelProperty("请求结果状态")
    @JsonProperty("Success")
    private Boolean success;

    @ApiModelProperty("请求结果状态码 200：成功")
    @JsonProperty("Code")
    private Integer code;

    @ApiModelProperty("额外信息")
    @JsonProperty("Message")
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
        this.code = EnumCommonException.SUCCESS.getErrorCode();
        return this;
    }

    /**
     * 默认错误返回结果
     *
     * @return
     */
    public ResultInfo<T> fail() {
        this.success = false;
        this.code = EnumCommonException.SYSTEM_INTERNAL_ANOMALY.getErrorCode();
        this.message = EnumCommonException.SYSTEM_INTERNAL_ANOMALY.getMessage();
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
        this.code = EnumCommonException.SYSTEM_INTERNAL_ANOMALY.getErrorCode();
        this.message = message;
        return this;
    }

    public ResultInfo<T> fail(Integer errorCode, String message) {
        this.success = false;
        this.code = errorCode;
        this.message = message;
        return this;
    }
}

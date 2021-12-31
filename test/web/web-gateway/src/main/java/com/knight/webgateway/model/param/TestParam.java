package com.knight.webgateway.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口测试查询参数
 *
 * @author TortoiseKnightB
 * @date 2021/12/24
 */
@Data
public class TestParam {

    @ApiModelProperty(value = "数据编号")
    // TODO @NotNull
    @JsonProperty("Id")
    private String id;

    @ApiModelProperty(value = "消息内容")
    @JsonProperty("Message")
    private String message;
}

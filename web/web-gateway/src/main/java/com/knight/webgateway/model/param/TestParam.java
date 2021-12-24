package com.knight.webgateway.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
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
}

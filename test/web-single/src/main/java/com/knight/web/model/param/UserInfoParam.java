package com.knight.web.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Data
@ApiModel(description = "查询用户信息接口入参")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoParam extends BaseRequestParam {

    @ApiModelProperty(value = "用户名称", required = true)
    private String name;
}

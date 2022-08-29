package com.knight.web.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TortoiseKnightB
 * @date 2022/08/29
 */
@Data
@ApiModel(description = "查询用户信息接口入参")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoListParam extends BaseRequestParam {

    @ApiModelProperty(value = "每页的数量，默认20条")
    private Integer pageSize;

    @ApiModelProperty(value = "当前页，默认第1页")
    private Integer pageNum;
}

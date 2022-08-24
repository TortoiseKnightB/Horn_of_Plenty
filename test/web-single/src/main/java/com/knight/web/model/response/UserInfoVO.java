package com.knight.web.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@ApiModel(description = "用户信息实体类VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {

    @ApiModelProperty("主键id")
    private int id;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}

package com.knight.webgateway.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.knight.webcommon.aspect.annotation.NotBlank;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author TortoiseKnightB
 * @date 2022/02/08
 */
@Data
public class ClassParam {

    @ApiModelProperty(value = "班级编号")
    @JsonProperty("ClassNO")
    @NotBlank(message = "ClassNO 不能为空")
    private String classNO;

    @ApiModelProperty(value = "班级人数")
    @JsonProperty("StudentNum")
    @NotBlank(message = "StudentNum 不能为空")
    private Integer studentNum;
}

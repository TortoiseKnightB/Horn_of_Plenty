package com.knight.webgateway.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.knight.webcommon.aspect.annotation.NotBlank;
import com.knight.webcommon.aspect.annotation.NotNullAnnotation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author TortoiseKnightB
 * @date 2022/02/08
 */
@Data
public class SchoolParam extends ArchitectureParam {

    @ApiModelProperty(value = "学校名称")
    @JsonProperty("Name")
    @NotBlank(message = "Name 不能为空")
    private String name;

    @ApiModelProperty(value = "班级")
    @JsonProperty("ClassParam")
    @NotBlank(message = "ClassParam 不能为空")
    @NotNullAnnotation
    private ClassParam classParam;
}

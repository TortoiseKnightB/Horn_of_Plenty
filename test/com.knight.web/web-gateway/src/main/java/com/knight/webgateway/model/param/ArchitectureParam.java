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
public class ArchitectureParam {

    @ApiModelProperty(value = "建筑高度")
    @JsonProperty("Height")
    @NotBlank(message = "Height 不能为空")
    private String height;

    @ApiModelProperty(value = "建筑宽度")
    @JsonProperty("Width")
    @NotBlank(message = "Width 不能为空")
    private String width;
}

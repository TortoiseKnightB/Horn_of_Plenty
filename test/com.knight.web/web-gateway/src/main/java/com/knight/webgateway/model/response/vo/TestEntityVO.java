package com.knight.webgateway.model.response.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试返回实体类VO
 *
 * @author TortoiseKnightB
 * @date 2021/12/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestEntityVO {
    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Long id;
    /**
     * 测试编号
     */
    @ApiModelProperty("测试编号")
    private String testId;
    /**
     * 数据消息
     */
    @ApiModelProperty("数据消息")
    private String message;
}

package com.knight.webcommon.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试数据库实体类DTO
 *
 * @author TortoiseKnightB
 * @date 2021/12/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestEntityDTO {
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

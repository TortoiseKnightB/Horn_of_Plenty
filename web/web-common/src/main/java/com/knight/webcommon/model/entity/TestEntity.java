package com.knight.webcommon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试数据库实体类
 *
 * @author TortoiseKnightB
 * @date 2021/12/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestEntity {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 测试编号
     */
    private String testId;
    /**
     * 数据消息
     */
    private String message;


}

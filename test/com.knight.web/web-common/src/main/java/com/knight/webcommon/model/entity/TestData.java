package com.knight.webcommon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库mapper入参
 *
 * @author TortoiseKnightB
 * @date 2021/12/30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestData {

    /**
     * 数据编号
     */
    private String testId;

    /**
     * 数据信息
     */
    private String message;
}

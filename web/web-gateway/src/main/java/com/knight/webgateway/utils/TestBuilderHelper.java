package com.knight.webgateway.utils;

import com.knight.webcommon.model.entity.TestEntityDTO;
import com.knight.webgateway.model.response.vo.TestEntityVO;

/**
 * 测试对象构建帮助类
 *
 * @author TortoiseKnightB
 * @date 2021/12/24
 */
public class TestBuilderHelper {

    private TestBuilderHelper() {
    }

    /**
     * 构建测试返回实体类VO
     *
     * @param dto 测试数据库实体类DTO
     * @return TestEntityVO
     */
    public static TestEntityVO build(TestEntityDTO dto) {
        return TestEntityVO.builder()
                .id(dto.getId())
                .testId(dto.getTestId())
                .message(dto.getMessage())
                .build();
    }


}

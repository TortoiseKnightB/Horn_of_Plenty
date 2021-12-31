package com.knight.webtest.utils;

import com.knight.webcommon.model.entity.TestData;

/**
 * 对象构建帮助类
 *
 * @author TortoiseKnightB
 * @date 2021/12/30
 */
public class BuildHelper {

    private BuildHelper() {
    }

    /**
     * 构建新增测试数据类mapper入参
     *
     * @param id      数据编号
     * @param message 数据信息
     * @return TestData
     */
    public static TestData build(String id, String message) {
        return TestData.builder()
                .testId(id)
                .message(message)
                .build();
    }

}

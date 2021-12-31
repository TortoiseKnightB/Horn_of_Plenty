package com.knight.webtest.dao.mapper;

import com.knight.webcommon.model.entity.TestData;
import com.knight.webcommon.model.entity.TestEntityDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 测试 Mapper
 *
 * @author TortoiseKnightB
 * @date 2021/12/10
 */
@Mapper
public interface TestMapper {
    /**
     * 查询测试数据
     *
     * @param testId 测试编号
     * @return 测试数据
     */
    TestEntityDTO getTestEntity(@Param("testId") String testId);


    /**
     * 插入一条测试数据
     *
     * @param data 插入数据的信息
     * @return
     */
    int insertTestData(TestData data);
}

package com.knight.webtest.dao.mapper;

import com.knight.webcommon.model.entity.TestEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author TortoiseKnightB
 * @date 2021/12/13
 */
@SpringBootTest
class TestMapperTest {

    @Resource
    private TestMapper testMapper;

    @Test
    void getTestEntity() {
        String testId = "2021cvi";
        TestEntity testEntity = testMapper.getTestEntity(testId);
        System.out.println(testEntity);
    }
}
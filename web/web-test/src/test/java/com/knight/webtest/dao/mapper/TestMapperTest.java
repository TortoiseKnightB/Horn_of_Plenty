package com.knight.webtest.dao.mapper;

import com.knight.webcommon.model.entity.TestEntityDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


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
        TestEntityDTO testEntityDTO = testMapper.getTestEntity(testId);
        System.out.println(testEntityDTO);
    }
}
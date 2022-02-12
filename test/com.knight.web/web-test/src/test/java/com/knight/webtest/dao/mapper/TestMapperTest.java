package com.knight.webtest.dao.mapper;

import com.knight.webcommon.model.entity.TestData;
import com.knight.webcommon.model.entity.TestEntityDTO;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("测试查询数据库信息")
    @Test
    void getTestEntity() {
        String testId = "test_id_99";
        TestEntityDTO testEntityDTO = testMapper.getTestEntity(testId);
        System.out.println(testEntityDTO);
    }

    @DisplayName("测试插入数据库信息")
    @Test
    void addTestData() {
        TestData testData = TestData.builder()
                .testId("test_id_98")
                .message("test_message_98")
                .build();
        int result = 0;
        try {
            result = testMapper.insertTestData(testData);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("异常，捕获失败信息！！！\ntest_id 可能重复");
        }
    }
}
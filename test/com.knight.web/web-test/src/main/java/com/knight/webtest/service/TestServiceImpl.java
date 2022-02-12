package com.knight.webtest.service;

import com.knight.webcommon.model.entity.TestData;
import com.knight.webcommon.model.entity.TestEntityDTO;
import com.knight.webcommon.service.TestService;
import com.knight.webtest.dao.mapper.TestMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author TortoiseKnightB
 * @date 2021/12/09
 */
@Component(value = "TestService-mid")
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public TestEntityDTO startTestService(String id) {
        return testMapper.getTestEntity(id);
    }

    @Override
    public int addTestData(TestData data) {
        return testMapper.insertTestData(data);
    }
}

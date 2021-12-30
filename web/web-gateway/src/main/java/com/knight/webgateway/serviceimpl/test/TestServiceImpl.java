package com.knight.webgateway.serviceimpl.test;

import cn.hutool.core.util.StrUtil;
import com.knight.webcommon.model.entity.TestEntityDTO;
import com.knight.webgateway.manager.test.TestManager;
import com.knight.webgateway.model.response.vo.TestEntityVO;
import com.knight.webgateway.service.test.TestService;
import com.knight.webgateway.utils.TestBuilderHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * controller 主要调用部分，作为外部依赖小模块的实现，其中可调用 manager 部分与中台通信
 *
 * @author TortoiseKnightB
 * @date 2021/12/24
 */
@Service(value = "TestService-gateway")
public class TestServiceImpl implements TestService {

    @Resource
    private TestManager testManager;

    /**
     * 网关接口测试 Service
     *
     * @param id 数据库 test_id
     * @return TestEntityVO
     */
    @Override
    public TestEntityVO startTestService(String id) {
        if (StrUtil.isBlank(id)) {
            // TODO 报错，需要配置全局异常拦截器
            return null;
        }
        TestEntityDTO testEntityDTO = testManager.startTestManager(id);
        return TestBuilderHelper.build(testEntityDTO);
    }

    /**
     * 新增测试数据
     *
     * @param id      测试数据编号
     * @param message 测试数据信息
     * @return Boolean
     */
    @Override
    public Boolean addTestData(String id, String message) {
        if (StrUtil.isBlank(id)) {
            // TODO 报错，需要配置全局异常拦截器
            return null;
        }
        return testManager.addTestData(id, message);
    }
}

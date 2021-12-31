package com.knight.webgateway.manager.test;

import com.knight.webcommon.api.TestApi;
import com.knight.webcommon.model.entity.TestEntityDTO;
import com.knight.webcommon.model.response.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 中台接口管理类
 * <p>
 * 调用 web-common 中的接口
 *
 * @author TortoiseKnightB
 * @date 2021/12/24
 */
@Slf4j
@Component
public class TestManager {

    @Resource
    private TestApi testApi;


    /**
     * 测试 manager 接口，调用中台的测试 api 接口
     *
     * @param id 数据库 test_id
     * @return
     */
    public TestEntityDTO startTestManager(String id) {
        ResultInfo<TestEntityDTO> resultInfo = testApi.startTestApi(id);
        if (resultInfo == null) {
            // TODO 抛出中台调用异常
        }
        if (resultInfo.getSuccess() == false) {
            // TODO 抛出调用结果返回失败异常
        }
        return resultInfo.getData();
    }

    public Boolean addTestData(String id, String message) {
        ResultInfo<Boolean> resultInfo = testApi.addTestData(id, message);
        if (resultInfo == null) {
            // TODO 抛出中台调用异常
        }
        if (resultInfo.getSuccess() == false) {
            // TODO 抛出调用结果返回失败异常
        }
        return resultInfo.getData();
    }
}

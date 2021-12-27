package com.knight.webtest.api;

import com.knight.webcommon.api.TestApi;
import com.knight.webcommon.model.entity.TestEntityDTO;
import com.knight.webcommon.model.response.ResultInfo;
import com.knight.webcommon.service.TestService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * 通过 controller 方式实现 api 的调用
 */
@Component
public class TestApiImpl implements TestApi {

    @Resource(name = "TestService-mid")
    private TestService testService;

    @GetMapping(value = "startTestApi")
    @Override
    public ResultInfo<TestEntityDTO> startTestApi(String id) {
        return new ResultInfo<TestEntityDTO>().succeed(testService.startTestService(id));
    }
}

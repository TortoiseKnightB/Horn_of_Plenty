package com.knight.webtest.api;

import com.knight.webcommon.api.TestApi;
import com.knight.webcommon.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 通过 controller 方式实现 api 的调用
 */
@RestController
@RequestMapping("test")
public class TestApiImpl implements TestApi {

    @Resource
    private TestService testService;

    @GetMapping(value = "startTestApi")
    @Override
    public String startTestApi(String id) {
        return testService.startTestService(id);
    }
}

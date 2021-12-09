package com.knight.webtest.api;

import com.knight.webcommon.api.TestApi;
import com.knight.webcommon.service.TestService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class TestApiImpl implements TestApi {

    @Resource
    private TestService testService;

    @Override
    public int startTestApi(int a, int b) {
        return testService.startTestService(a, b);
    }
}

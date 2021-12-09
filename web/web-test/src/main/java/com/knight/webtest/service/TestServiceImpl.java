package com.knight.webtest.service;

import com.knight.webcommon.service.TestService;
import org.springframework.stereotype.Component;

/**
 * @author TortoiseKnightB
 * @date 2021/12/09
 */
@Component
public class TestServiceImpl implements TestService {

    @Override
    public int startTestService(int a, int b) {
        return a + b;
    }
}

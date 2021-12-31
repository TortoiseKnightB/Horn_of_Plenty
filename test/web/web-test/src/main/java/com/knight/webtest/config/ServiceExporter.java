package com.knight.webtest.config;

import com.knight.webcommon.api.TestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author TortoiseKnightB
 * @date 2021/12/09
 */
@Slf4j
@Component
public class ServiceExporter implements CommandLineRunner {

    @Resource
    private TestApi testApi;


    @Override
    public void run(String... args) throws Exception {

    }
}

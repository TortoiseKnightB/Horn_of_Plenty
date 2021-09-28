package com.knight.multi_level_cache.controller;

import com.knight.multi_level_cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tortoiseKnight
 * @date 2021/09/28
 */
@RestController
@Slf4j
@RequestMapping(value = "/cache")
public class CacheController {

    @Resource
    private CacheService cacheService;

    @PostMapping("/testCache")
    public void testCache() {
        log.info("**[CacheController]** 开始：测试接口调用");
        String result = cacheService.testFunction();
        log.info("**[CacheController]** 结束：测试接口调用");
    }
}

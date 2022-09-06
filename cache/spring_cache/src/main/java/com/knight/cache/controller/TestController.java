package com.knight.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TortoiseKnightB
 * @date 2022/09/05
 */
@RestController()
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/info")
    public void info() {
        Cache cache = cacheManager.getCache("tcache01");
        System.out.println(cache);
    }


    @Cacheable("tcache01")
    @GetMapping("/cache01")
    public String testCache01() {
        System.out.println("不走缓存，执行接口 testCache01");
        return "result testCache01";
    }


}

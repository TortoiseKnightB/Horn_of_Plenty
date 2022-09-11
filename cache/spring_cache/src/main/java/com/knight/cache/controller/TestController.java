package com.knight.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/info")
    public void info() {
        Cache cache = cacheManager.getCache("tcache01");
        System.out.println(cache);
    }


    @Cacheable(cacheNames = "tcache01", key = "#id")
    @GetMapping("/cache01")
    public String testCache01(Integer id) {
        System.out.println("模拟去db查询~~~cache01: " + id);
        return "result testCache01";
    }

    @Cacheable(cacheNames = "tcache02", keyGenerator = "keyGenerator")
    @GetMapping("/cache02")
    public String testCache02(Integer id) {
        System.out.println("模拟去db查询~~~cache02: " + id);
        return "result testCache02";
    }

    @GetMapping("/cache03")
    public String testCache03(Integer id) {
        System.out.println("模拟去db查询~~~cache03: " + id);
        redisTemplate.opsForValue().set("redistemplate", "result testCache03");
        return "result testCache03";
    }


}

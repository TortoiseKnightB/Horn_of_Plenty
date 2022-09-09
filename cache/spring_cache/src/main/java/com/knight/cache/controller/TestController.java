package com.knight.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


    @Cacheable(cacheNames = "tcache01", key = "#id")
    @PostMapping("/cache01")
    public String testCache01(Integer id) {
        System.out.println("模拟去db查询~~~" + id);
        return "result testCache01";
    }


}

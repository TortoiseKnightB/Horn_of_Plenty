package com.knight.cache.customizedcache.controller;

import com.knight.cache.customizedcache.CustomizedCacheApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TortoiseKnightB
 * @date 2022/09/14
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/info")
    public String info() {
//        for (String beanDefinitionName : CustomizedCacheApplication.applicationContext.getBeanDefinitionNames()) {
//            System.out.println(beanDefinitionName);
//        }
        CacheManager cacheManager = (CacheManager) CustomizedCacheApplication.applicationContext.getBean("cacheManager");
        for (String cacheName : cacheManager.getCacheNames()) {
            System.out.println(cacheName);
        }
        Cache mycache = cacheManager.getCache("mycache");
//        if (mycache != null) {
//            Cache.ValueWrapper valueWrapper = caffeineCache.get(caffeineCacheKey);
//            System.out.println(valueWrapper.get());
//        }
        return "Customized-Cache ready";
    }

    @Cacheable(cacheNames = "mycache")
    @GetMapping("/cache01")
    public String cache01(int id) {
        System.out.println("cache01 缓存未击中: " + id);
        return "result cache01 RedisCache";
    }
}

package com.knight.cache.caffeineredis.controller;

import com.knight.cache.caffeineredis.CaffeineRedisApplication;
import com.knight.cache.caffeineredis.consts.CacheConstant;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TortoiseKnightB
 * @date 2022/09/13
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final String caffeineCacheName = "caffeineCache";
    private String caffeineCacheKey;

    @GetMapping("/info")
    public String info() {
        CacheManager cacheManager = (CacheManager) CaffeineRedisApplication.applicationContextl.getBean(CacheConstant.CAFFEINE_CACHE_MANAGER);
        for (String cacheName : cacheManager.getCacheNames()) {
            System.out.println(cacheName);
        }
        Cache caffeineCache = cacheManager.getCache(caffeineCacheName);
        if (caffeineCache != null) {
            Cache.ValueWrapper valueWrapper = caffeineCache.get(caffeineCacheKey);
            System.out.println(valueWrapper.get());
        }
        return "caffeine-redis ready";
    }

    /**
     * 默认使用Redis缓存
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "XXXX")
    @GetMapping("/cache01")
    public String cache01(int id) {
        System.out.println("cache01 缓存未击中: " + id);
        return "result cache01 RedisCache";
    }

    /**
     * 指定使用Caffeine缓存
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = caffeineCacheName, cacheManager = CacheConstant.CAFFEINE_CACHE_MANAGER, key = "'caffeineKey' + #id")
    @GetMapping("/cache02")
    public String cache02(int id) {
        caffeineCacheKey = "caffeineKey" + id;
        System.out.println("cache02 缓存未击中: " + id);
        return "result cache02 CaffeineCache";
    }

    /**
     * 指定使用Redis缓存
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "redisCache", cacheManager = CacheConstant.REDIS_CACHE_MANAGER, keyGenerator = "keyGenerator")
    @GetMapping("/cache03")
    public String cache03(int id) {
        System.out.println("cache03 缓存未击中: " + id);
        return "result cache03 RedisCache";
    }

}

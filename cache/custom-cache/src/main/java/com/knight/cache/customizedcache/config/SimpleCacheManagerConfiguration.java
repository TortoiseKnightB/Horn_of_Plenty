package com.knight.cache.customizedcache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TortoiseKnightB
 * @date 2022/09/14
 */
@Configuration
@EnableCaching
public class SimpleCacheManagerConfiguration {

    @Bean
    public CacheManager cacheManager() {
        MyCacheManager cacheManager = new MyCacheManager();
        List<MyCache> caches = new ArrayList<MyCache>();
//        caches.add(new MyCache("mycache"));
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}

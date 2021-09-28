package com.knight.multi_level_cache.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tortoiseKnight
 * @date 2021/09/27
 */
@Slf4j
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    @Override
    public CacheManager cacheManager() {
        log.info("**[CacheConfig]** MultiCacheManager is to be Initialized");
        return new MultiCacheManager();
    }
}

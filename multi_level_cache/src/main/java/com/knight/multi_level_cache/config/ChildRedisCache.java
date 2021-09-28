package com.knight.multi_level_cache.config;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

/**
 * ChildRedisCache is designed to construct RedisCache cause the constructor of RedisCache is protected.
 *
 * @author tortoiseKnight
 * @date 2021/09/27
 */
public class ChildRedisCache extends RedisCache {
    public ChildRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
        super(name, cacheWriter, cacheConfig);
    }
}

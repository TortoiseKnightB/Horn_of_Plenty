package com.knight.cache.rediscaffeinecache.util;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author TortoiseKnightB
 * @date 2022/09/15
 */
public class L2CacheManager implements CacheManager {

    /**
     * CacheManager管理的所有缓存（CacheName=>Cache）
     */
    private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>();

    /**
     * CacheManager管理的所有缓存的名称
     */
    private Set<String> cacheNames = Collections.emptySet();

    private RedisTemplate<String, Object> redisTemplate;

    public L2CacheManager(ConcurrentMap<String, Cache> cacheMap, Set<String> cacheNames, RedisTemplate<String, Object> redisTemplate) {
        this.cacheMap = cacheMap;
        this.cacheNames = cacheNames;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Cache getCache(String name) {
        return null;
    }

    @Override
    public Collection<String> getCacheNames() {
        return null;
    }
}

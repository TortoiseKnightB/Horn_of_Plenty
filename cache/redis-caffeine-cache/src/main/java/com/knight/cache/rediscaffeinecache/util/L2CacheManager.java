package com.knight.cache.rediscaffeinecache.util;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author TortoiseKnightB
 * @date 2022/09/15
 */
public class L2CacheManager implements CacheManager {

    /**
     * CacheManager管理的所有缓存（CacheName=>L2Cache），不用事先声明，会自动创建
     */
    private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>();

    /**
     * CacheManager管理的所有缓存的名称
     */
    private Set<String> cacheNames;

    private RedisTemplate<String, Object> redisTemplate;

    private com.github.benmanes.caffeine.cache.Cache<Object, Object> caffeine;

    private String prefix;


    public L2CacheManager(Set<String> cacheNames, RedisTemplate<String, Object> redisTemplate,
                          com.github.benmanes.caffeine.cache.Cache<Object, Object> caffeine, String prefix) {
        this.cacheNames = cacheNames;
        this.redisTemplate = redisTemplate;
        this.caffeine = caffeine;
        this.prefix = prefix;
    }

    // TODO：所有缓存都用的同一个redisTemplate和caffeine？待改为分别使用
    @Override
    public Cache getCache(String name) {
        Cache cache = cacheMap.get(name);
        if (cache != null) {
            return cache;
        }
        cache = new L2Cache(name, caffeine, redisTemplate, prefix);
        // TODO: cacheNames里面不用新增？
        Cache oldCache = cacheMap.putIfAbsent(name, cache);
        return oldCache == null ? cache : oldCache;
    }

    @Override
    public Collection<String> getCacheNames() {
        return this.cacheNames;
    }
}

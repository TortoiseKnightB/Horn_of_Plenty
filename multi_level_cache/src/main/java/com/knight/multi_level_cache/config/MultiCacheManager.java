package com.knight.multi_level_cache.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author tortoiseKnight
 * @date 2021/09/27
 */
@Slf4j
public class MultiCacheManager implements CacheManager {

    private static final Map<String, MultiCache> cacheMap = new HashMap<>(4);

    public MultiCacheManager() {
        log.info("**[MultiCacheManager]** MultiCacheManager Initialized");
    }

    /**
     * Get the cache associated with the given name.
     */
    @Override
    public Cache getCache(String cacheName) {
        MultiCache multiCache = cacheMap.get(cacheName);
        if (multiCache != null) {
            log.info("**[MultiCacheManager]** MultiCache[{}] exists, got it", cacheName);
            return multiCache;
        }

        // TODO 加锁？
        multiCache = createCache(cacheName);
        cacheMap.put(cacheName, multiCache);
        log.info("**[MultiCacheManager]** MultiCache[{}] not exists, created it", cacheName);
        return multiCache;
    }

    /**
     * Get a collection of the cache names known by this manager.
     */
    @Override
    public Collection<String> getCacheNames() {
        Set<String> cacheNames = cacheMap.keySet();
        return cacheNames;
    }

    private MultiCache createCache(String cacheName) {
        return new MultiCache(cacheName);
    }
}

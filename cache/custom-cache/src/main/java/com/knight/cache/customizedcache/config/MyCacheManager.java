package com.knight.cache.customizedcache.config;

import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;

/**
 * @author TortoiseKnightB
 * @date 2022/09/13
 */
public class MyCacheManager extends AbstractCacheManager {
    private Collection<? extends MyCache> caches;

    public void setCaches(Collection<? extends MyCache> caches) {
        this.caches = caches;
    }

    @Override
    protected Collection<? extends MyCache> loadCaches() {
        return this.caches;
    }
}

package com.knight.multi_level_cache.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.util.concurrent.Callable;

/**
 * @author tortoiseKnight
 * @date 2021/09/27
 */
@Getter
@Slf4j
public class MultiCache extends AbstractValueAdaptingCache {

    private ApplicationContext applicationContext;


    private final String cacheName;

    private final RedisCache redisCache;

    public MultiCache(String cacheName) {
        super(true);
        this.cacheName = cacheName;

        RedisCacheWriter redisCacheWriter = applicationContext.getBean(RedisCacheWriter.class);
        RedisCacheConfiguration redisCacheConfiguration = applicationContext.getBean(RedisCacheConfiguration.class);

        this.redisCache = new ChildRedisCache(cacheName, redisCacheWriter, redisCacheConfiguration);
    }

    @Override
    protected Object lookup(Object key) {
        log.info("**[MultiCache]** lookup");
        return null;
    }

    @Override
    public String getName() {
        log.info("**[MultiCache]** getName");
        return null;
    }

    @Override
    public Object getNativeCache() {
        log.info("**[MultiCache]** getNativeCache");
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        log.info("**[MultiCache]** get");
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        log.info("**[MultiCache]** put");
    }

    @Override
    public void evict(Object key) {
        log.info("**[MultiCache]** evict");
    }

    @Override
    public void clear() {
        log.info("**[MultiCache]** clear");
    }
}

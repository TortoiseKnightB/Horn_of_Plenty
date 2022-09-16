package com.knight.cache.rediscaffeinecache.util;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author TortoiseKnightB
 * @date 2022/09/15
 */
@Slf4j
public class L2Cache extends AbstractValueAdaptingCache {

    private String cacheName;

    private Cache<Object, Object> caffeine;

    private RedisTemplate<String, Object> redisTemplate;

    private String prefix;

    /**
     * 在{@link #get(Object, Callable)}中加锁使用，同步读取数据。不用事先赋值，会自动创建
     */
    private Map<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();


    protected L2Cache(boolean allowNullValues) {
        super(allowNullValues);
    }

    public L2Cache(String cacheName, Cache<Object, Object> caffeine, RedisTemplate<String, Object> redisTemplate, String prefix) {
        super(true);
        this.cacheName = cacheName;
        this.caffeine = caffeine;
        this.redisTemplate = redisTemplate;
        this.prefix = prefix;
    }

    public L2Cache(boolean allowNullValues, String cacheName, Cache<Object, Object> caffeine, RedisTemplate<String, Object> redisTemplate, String prefix) {
        super(allowNullValues);
        this.cacheName = cacheName;
        this.caffeine = caffeine;
        this.redisTemplate = redisTemplate;
        this.prefix = prefix;
    }

    @Override
    protected Object lookup(Object key) {
        String cacheKey = getKey(key);
        // 尝试从一级缓存中获取数据
        Object value = caffeine.getIfPresent(cacheKey);
        if (value != null) {
            log.info("get cache from Caffeine, key:" + cacheKey + ",value:" + value);
            return value;
        }
        // 一级缓存中没有，从二级缓存获取，并更新一级缓存
        value = redisTemplate.opsForValue().get(cacheKey);
        if (value != null) {
            log.info("get cache from Redis, key:" + cacheKey + ",value:" + value);
            caffeine.put(cacheKey, value);
        }
        return value;
    }

    @Override
    public String getName() {
        return this.cacheName;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        Object value = lookup(key);
        if (value != null) {
            return (T) value;
        }
        // 同步加载
        ReentrantLock lock = lockMap.get(getKey(key));
        if (lock == null) {
            lock = new ReentrantLock();
            lockMap.putIfAbsent(getKey(key), lock);
        }
        lock.lock();
        try {
            value = lookup(key);
            if (value != null) {
                return (T) value;
            }
            value = valueLoader.call();
            put(key, value);
            return (T) value;
        } catch (Exception e) {
            throw new ValueRetrievalException(key, valueLoader, e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void put(Object key, Object value) {
        String cacheKey = getKey(key);
        // 是否存储null
        if (!super.isAllowNullValues() && value == null) {
            evict(key);
        }
        redisTemplate.opsForValue().set(cacheKey, toStoreValue(value));
        caffeine.put(cacheKey, toStoreValue(value));
    }

    @Override
    public void evict(Object key) {
        // 先清除redis中缓存数据，然后清除caffeine中的缓存。避免清除caffeine缓存后其他请求命中redis，又更新caffeine
        redisTemplate.delete(getKey(key));
        caffeine.invalidate(getKey(key));
    }

    @Override
    public void clear() {
        // 先清除redis中缓存数据，然后清除caffeine中的缓存。避免清除caffeine缓存后其他请求命中redis，又更新caffeine
        Set<String> keys = redisTemplate.keys(cacheName.concat(":*"));
        for (String key : keys) {
            redisTemplate.delete(key);
        }
        caffeine.invalidateAll(keys);
    }

    /**
     * 处理key的前缀和类型
     *
     * @param key
     * @return
     */
    private String getKey(Object key) {
        StringBuilder cacheKey = new StringBuilder(cacheName);
        if (prefix != null && prefix.length() != 0) {
            cacheKey.append(":").append(prefix);
        }
        cacheKey.append(":").append(key.toString());
        return cacheKey.toString();
    }


}

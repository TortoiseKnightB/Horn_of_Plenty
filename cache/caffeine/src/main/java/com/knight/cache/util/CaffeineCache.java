package com.knight.cache.util;

import com.github.benmanes.caffeine.cache.stats.CacheStats;

import java.util.concurrent.TimeUnit;

/**
 * 自定义实现Caffeine
 *
 * @author TortoiseKnightB
 * @date 2022/08/28
 */
public interface CaffeineCache<K, V> {

    void put(K key, V value, long expire, TimeUnit timeUnit);

    V get(K key);

    void expire(K key, long expire, TimeUnit timeUnit);

    boolean containsKey(K key);

    void invalidate(K key);

    CacheStats cacheStates();

    long keySize();
}

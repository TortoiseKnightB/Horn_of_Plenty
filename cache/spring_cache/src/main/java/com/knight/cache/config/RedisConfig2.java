package com.knight.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

/**
 * @author TortoiseKnightB
 * @date 2022/09/09
 */
@EnableCaching  // 使用了CacheManager，别忘了开启它  否则无效
@Configuration
public class RedisConfig2 extends CachingConfigurerSupport {

    // 配置一个CacheManager 来支持缓存注解
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1)) //Duration.ZERO表示永不过期（此值一般建议必须设置）
                //.disableKeyPrefix() // 禁用key的前缀
                //.disableCachingNullValues() //禁止缓存null值

                //=== 前缀我个人觉得是非常重要的，建议约定：注解缓存一个统一前缀、RedisTemplate直接操作的缓存一个统一前缀===
                //.prefixKeysWith("baidu:") // 底层其实调用的还是computePrefixWith() 方法，只是它的前缀是固定的（默认前缀是cacheName，此方法是把它固定住，一般不建议使用固定的）
                .computePrefixWith(CacheKeyPrefix.simple()); // 使用内置的实现
//                .computePrefixWith(cacheName -> "caching:" + cacheName) // 自己实现，建议这么使用(cacheName也保留下来了)
                ;

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(factory)
                // .disableCreateOnMissingCache() // 关闭动态创建Cache
                //.initialCacheNames() // 初始化时候就放进去的cacheNames（若关闭了动态创建，这个就是必须的）
                .cacheDefaults(configuration) // 默认配置（强烈建议配置上）。  比如动态创建出来的都会走此默认配置
                //.withInitialCacheConfigurations() // 个性化配置  可以提供一个Map，针对每个Cache都进行个性化的配置（否则是默认配置）
                //.transactionAware() // 支持事务
                .build();
        return redisCacheManager;
    }

}

package com.knight.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TortoiseKnightB
 * @date 2022/09/09
 */
@EnableCaching  // 使用了CacheManager，别忘了开启它  否则无效
//@Configuration
public class RedisConfig2 extends CachingConfigurerSupport {

    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;

    // 配置一个CacheManager 来支持缓存注解
    @Bean
    @Override
    public CacheManager cacheManager() {

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围.ANY指都有,从private到public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会抛出异常
        // 使存储到redis里的数据是有类型的json数据，如：["com.knight.cache.model.User",{"username":"Ben","age":24}]
        // 不使用则存储纯Json数据，如：{"username":"Alen","age":24}
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1)) //Duration.ZERO表示永不过期（此值一般建议必须设置）
                //.disableKeyPrefix() // 禁用key的前缀
                //.disableCachingNullValues() //禁止缓存null值

                //=== 前缀我个人觉得是非常重要的，建议约定：注解缓存一个统一前缀、RedisTemplate直接操作的缓存一个统一前缀===
                //.prefixKeysWith("baidu:") // 底层其实调用的还是computePrefixWith() 方法，只是它的前缀是固定的（默认前缀是cacheName，此方法是把它固定住，一般不建议使用固定的）
//                .computePrefixWith(CacheKeyPrefix.simple()); // 使用内置的实现
                .computePrefixWith(cacheName -> "caching:" + cacheName + ":") // 自己实现，建议这么使用(cacheName也保留下来了)
                // 设置 key为string序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置value为json序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));

        // 对每个缓存空间应用不同的配置
        Set<String> cacheNames = new HashSet<>();
        cacheNames.add("tcache01");
        cacheNames.add("tcache02");
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("tcache01", configuration.entryTtl(Duration.ofSeconds(30)));
        configMap.put("tcache02", configuration.entryTtl(Duration.ofSeconds(40)));

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(lettuceConnectionFactory)
                // .disableCreateOnMissingCache() // 关闭动态创建Cache
                .cacheDefaults(configuration) // 默认配置（强烈建议配置上）。  比如动态创建出来的都会走此默认配置
                .initialCacheNames(cacheNames) // 初始化时候就放进去的cacheNames（若关闭了动态创建，这个就是必须的）
                .withInitialCacheConfigurations(configMap) // 个性化配置  可以提供一个Map，针对每个Cache都进行个性化的配置（否则是默认配置）
                //.transactionAware() // 支持事务
                .build();
        return redisCacheManager;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName()).append(".");
                sb.append(method.getName());
                sb.append("(");
                for (Object param : params) {
                    sb.append(param.toString());
                    sb.append(",");
                }
                sb.append(")");
                return sb.toString();
            }
        };
    }
}

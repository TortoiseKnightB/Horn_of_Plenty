package com.knight.cache.rediscaffeinecache.service;

import com.knight.cache.rediscaffeinecache.model.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TortoiseKnightB
 * @date 2022/09/16
 */
@Service
@Slf4j
public class L2CacheService {

    @Autowired
    private KeyGenerator keyGenerator;

    /**
     * 模拟数据库
     */
    private static Map<String, UserDO> userMap = new HashMap<>();

    static {
        userMap.put("user01", new UserDO("Alen", 24));
        userMap.put("user02", new UserDO("Ben", 23));
        userMap.put("user03", new UserDO("Cat", 18));
        userMap.put("user04", new UserDO("Dio", 35));
    }

    /**
     * 查询单个用户
     *
     * @param userId
     * @return
     */
    @Cacheable(cacheManager = "l2CacheManager", cacheNames = "userCache", keyGenerator = "keyGenerator")
    public UserDO queryUser(String userId) {
        UserDO userDO = userMap.get(userId);
        if (userDO == null) {
            log.info("数据库查询失败，userId:" + userId);
        } else {
            log.info("数据库查询数据：" + userDO.toString());
        }
        return userDO;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Cacheable(cacheManager = "l2CacheManager", cacheNames = "userAll", keyGenerator = "keyGenerator")
    public List<UserDO> queryUserAll() {
        List<UserDO> userAll = new ArrayList<>(userMap.values());
        log.info("数据库查询数据：" + userAll.toString());
        return userAll;
    }

    // TODO: key的生成不适用keyGenerator，可以考虑用方法getCacheKey生成

    /**
     * 清除单个用户缓存
     *
     * @param userId
     * @return
     */
    @CacheEvict(cacheManager = "l2CacheManager", cacheNames = "userCache")
    public String clear(String userId) {
        return "clear cache, key:" + userId;
    }

    // TODO：为CacheEvict生成key
    private String getCacheKey(Object target, Method method, Object... params) {
        Object cacheKey = keyGenerator.generate(target, method, params);
        return cacheKey.toString();
    }
}

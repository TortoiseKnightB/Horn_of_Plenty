package com.knight.cache.rediscaffeinecache.service;

import com.knight.cache.rediscaffeinecache.model.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TortoiseKnightB
 * @date 2022/09/16
 */
@Service
@Slf4j
public class L2CacheService {

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

    @Cacheable(cacheNames = "query")
    public UserDO queryUser(String userId) {
        UserDO userDO = userMap.get(userId);
        log.info("数据库查询数据：" + userDO.toString());
        return userDO;
    }
}

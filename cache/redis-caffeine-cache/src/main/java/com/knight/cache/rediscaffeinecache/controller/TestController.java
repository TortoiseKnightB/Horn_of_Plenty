package com.knight.cache.rediscaffeinecache.controller;

import com.knight.cache.rediscaffeinecache.RedisCaffeineCacheApplication;
import com.knight.cache.rediscaffeinecache.model.UserDO;
import com.knight.cache.rediscaffeinecache.service.L2CacheService;
import com.knight.cache.rediscaffeinecache.util.L2CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author TortoiseKnightB
 * @date 2022/09/16
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private L2CacheService l2CacheService;


    @GetMapping("/info")
    public String info() {
//        for (String beanDefinitionName : RedisCaffeineCacheApplication.applicationContext.getBeanDefinitionNames()) {
//            System.out.println(beanDefinitionName);
//        }
//        for (String s : RedisCaffeineCacheApplication.applicationContext.getBeanNamesForType(L2CacheManager.class)) {
//            System.out.println(s);
//        }
        L2CacheManager cacheManager = RedisCaffeineCacheApplication.applicationContext.getBean("l2CacheManager", L2CacheManager.class);
        System.out.println(cacheManager);
        Cache cache = cacheManager.cacheMap.get("userCache");
        System.out.println(cache.toString());

        return "run seccess";
    }

    @GetMapping("/query")
    public UserDO query(String userId) {
        UserDO userDO = l2CacheService.queryUser(userId);
        System.out.println(userDO);
        return userDO;
    }

    @GetMapping("/queryAll")
    public List<UserDO> queryAll() {
        List<UserDO> userAll = l2CacheService.queryUserAll();
        System.out.println(userAll.toString());
        return userAll;
    }

    @GetMapping("/clear")
    public String clear(String userId) {
        System.out.println("clear " + userId);
        return l2CacheService.clear(userId);
    }
}

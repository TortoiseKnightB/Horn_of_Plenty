package com.knight.cache.rediscaffeinecache.controller;

import com.knight.cache.rediscaffeinecache.RedisCaffeineCacheApplication;
import com.knight.cache.rediscaffeinecache.model.UserDO;
import com.knight.cache.rediscaffeinecache.service.L2CacheService;
import com.knight.cache.rediscaffeinecache.util.L2CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        L2CacheManager cacheManager = RedisCaffeineCacheApplication.applicationContext.getBean("cacheManager", L2CacheManager.class);
        System.out.println(cacheManager);

        return "run seccess";
    }

    @GetMapping("/query")
    public UserDO query(String userId) {
        UserDO userDO = l2CacheService.queryUser(userId);
        System.out.println(userDO);
        return userDO;
    }
}

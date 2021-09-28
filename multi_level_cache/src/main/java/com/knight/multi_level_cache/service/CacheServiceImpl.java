package com.knight.multi_level_cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author tortoiseKnight
 * @date 2021/09/28
 */
@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

    @Cacheable(value = "testFunction")
    @Override
    public String testFunction() {
        log.info("**[CacheServiceImpl]** 开始；多级缓存测试方法");
        log.info("**[CacheServiceImpl]** 结束；多级缓存测试方法");
        return null;
    }
}

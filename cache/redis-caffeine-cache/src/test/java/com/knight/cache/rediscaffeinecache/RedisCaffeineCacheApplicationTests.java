package com.knight.cache.rediscaffeinecache;

import com.knight.cache.rediscaffeinecache.model.UserDO;
import com.knight.cache.rediscaffeinecache.service.L2CacheService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RedisCaffeineCacheApplicationTests {

    @Autowired
    private L2CacheService l2CacheService;

    @DisplayName("基本测试")
    @Test
    void test01() {




        String userId = "user01";
        log.info("第1次查询");
        UserDO userDO1 = l2CacheService.queryUser(userId);
        log.info("查询结果：" + userDO1);

        log.info("第2次查询");
        UserDO userDO2 = l2CacheService.queryUser(userId);
        log.info("查询结果：" + userDO2);

        log.info("第3次查询");
        UserDO userDO3 = l2CacheService.queryUser(userId);
        log.info("查询结果：" + userDO3);
    }

}

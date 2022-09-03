package com.knight.cache;

import com.knight.cache.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author TortoiseKnightB
 * @date 2022/09/03
 */
@SpringBootTest
class RedisApplicationTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test01() {
        String key = "k2";
//        String value = "v3";
        User user = User.builder()
                .username("John")
                .age(24)
                .build();
        redisTemplate.opsForValue().set(key, user);
        Object result = redisTemplate.opsForValue().get(key);
        System.out.println(result.toString());
    }

}
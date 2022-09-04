package com.knight.cache;

import com.knight.cache.model.User;
import com.knight.cache.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;


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


    @Test
    public void test02() {
        String key = "k_test02";
        User user = User.builder()
                .username("King")
                .age(25)
                .build();
        System.out.println("存入" + key);
        RedisUtil.set(key, user);
        Object o = RedisUtil.get(key);
        System.out.println("获取" + key + "：" + o);
        System.out.println("判断存在：" + RedisUtil.hasKey(key));
        System.out.println("删除" + key);
        RedisUtil.del(key);
        System.out.println("判断存在：" + RedisUtil.hasKey(key));
    }

    @Test
    public void test03() throws InterruptedException {
        String key = "k_test03";
        User user = User.builder()
                .username("Ben")
                .age(25)
                .build();
        System.out.println("存入" + key);
        RedisUtil.set(key, user);
        System.out.println("获取缓存时间：" + RedisUtil.getExpire(key));
        int time = 3;
        System.out.println("设置缓存时间 " + time + " seconds");
        RedisUtil.expire(key, 3);
        System.out.println("获取缓存时间：" + RedisUtil.getExpire(key));
        Thread.sleep(3000);
        System.out.println("判断存在：" + RedisUtil.hasKey(key));
    }

    @Test
    public void test04() {
        String key1 = "k_test03_1";
        String key2 = "k_test03_2";
        String key3 = "k_test03_3";
        User user1 = User.builder()
                .username("Alen")
                .age(25)
                .build();
        User user2 = User.builder()
                .username("Ben")
                .age(25)
                .build();
        User user3 = User.builder()
                .username("Chaos")
                .age(25)
                .build();
        RedisUtil.set(key1, user1);
        RedisUtil.set(key2, user2);
        RedisUtil.set(key3, user3);
        Set keys = redisTemplate.keys("*");
        RedisUtil.del(key1, key2, key3);
        System.out.println(keys);
        Set keys2 = redisTemplate.keys("*");
        System.out.println(keys2);
    }

}
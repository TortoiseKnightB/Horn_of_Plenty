package com.knight.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author TortoiseKnightB
 * @date 2022/09/05
 */
@SpringBootApplication
@EnableCaching
public class springCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(springCacheApplication.class, args);
    }
}

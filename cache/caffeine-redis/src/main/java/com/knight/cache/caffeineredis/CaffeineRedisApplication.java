package com.knight.cache.caffeineredis;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CaffeineRedisApplication {

    public static ApplicationContext applicationContextl;

    public static void main(String[] args) {
        applicationContextl = SpringApplication.run(CaffeineRedisApplication.class, args);
    }

}

package com.knight.logback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TortoiseKnightB
 * @date 2022/08/23
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}

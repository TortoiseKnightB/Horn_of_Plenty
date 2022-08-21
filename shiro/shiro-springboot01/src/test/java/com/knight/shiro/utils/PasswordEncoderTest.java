package com.knight.shiro.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author TortoiseKnightB
 * @date 2022/08/21
 */
class PasswordEncoderTest {

    @Test
    void encoder() {
        String password = "123123";
        String encoder = PasswordEncoder.encoder(password);
        System.out.println(encoder);
    }
}
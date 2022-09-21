package com.knight.shiro.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.knight.shiro.model.User;
import com.knight.shiro.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author TortoiseKnightB
 * @date 2022/09/20
 */
@RestController
@RequestMapping("/jwt")
public class TestController {

    @GetMapping("/home")
    public String home() {
        return "connect success";
    }


    @GetMapping("/generateToken")
    public String generateToken(String userName, String pwd) throws UnsupportedEncodingException {
        System.out.println("假设校验通过，生成token");
        User user = User.builder()
                .id(66)
                .name(userName)
                .addr("上海")
                .build();
        String token = JwtUtil.generateToken(user);
        System.out.println(user);
        System.out.println(token);
        return token;
    }

    @GetMapping("/verifyToken")
    public User verifyToken(String token) throws UnsupportedEncodingException {
        System.out.println("查询用户列表，验证token：" + token);
        DecodedJWT decodedJWT = JwtUtil.verifyToken(token);
        User user = JwtUtil.parse(decodedJWT);
        System.out.println(user);
        return user;
    }
}

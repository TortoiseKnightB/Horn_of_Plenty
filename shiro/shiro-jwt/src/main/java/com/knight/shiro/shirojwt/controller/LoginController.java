package com.knight.shiro.shirojwt.controller;

import com.knight.shiro.shirojwt.model.JwtToken;
import com.knight.shiro.shirojwt.model.User;
import com.knight.shiro.shirojwt.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Account;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TortoiseKnightB
 * @date 2022/09/22
 */
@RestController
public class LoginController {


    @GetMapping("/accountLogin")
    public String AccountLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            String token = JwtUtil.sign(username, password);
            // 从subject.login进入认证模块
            subject.login(new JwtToken(token));
            // 设置session，给浏览器设置cookie
//            subject.getSession().setAttribute("token", subject.getPrincipal());
            return subject.getPrincipal().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login failed";
    }

    @GetMapping("/user")
    public String user() {
        return "find user success";
    }
}

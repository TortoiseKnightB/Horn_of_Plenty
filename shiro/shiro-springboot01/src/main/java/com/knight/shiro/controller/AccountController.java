package com.knight.shiro.controller;

import com.knight.shiro.entity.Account;
import com.knight.shiro.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TortoiseKnightB
 * @date 2022/08/21
 */
@RestController
//@RequestMapping("/account")
public class AccountController {

    /**
     * 无需登录
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    /**
     * 需要登录
     *
     * @return
     */
    @GetMapping("/main")
    public String main() {
        return "main authc";
    }


    /**
     * 需要manage权限
     *
     * @return
     */
    @GetMapping("/manage")
    public String manage() {
        return "manage perms[manage]";
    }


    /**
     * 需要administrator身份
     *
     * @return
     */
    @GetMapping("/administrator")
    public String administrator() {
        return "roles[administrator]";
    }


    /**
     * 登录操作
     * <p>
     * 数据库存储的密码为"123123"经过MD5加盐（"salt"）3次迭代
     *
     * @param username
     * @param password
     */
    @PostMapping("/accountLogin")
    public String AccountLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            // 从subject.login进入认证模块
            subject.login(usernamePasswordToken);
            Account account = (Account) subject.getPrincipal();
            // 设置session，给浏览器设置cookie
            subject.getSession().setAttribute("account", account);
            return "login success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login failed";
    }


    /**
     * 注销操作
     *
     * @return
     */
    @GetMapping("/logout")
    public String Logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout success";
    }

}

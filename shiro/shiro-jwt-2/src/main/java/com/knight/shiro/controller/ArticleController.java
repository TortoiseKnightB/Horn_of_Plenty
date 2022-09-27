package com.knight.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DisabledSessionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色、权限测试类
 */
@RestController
@RequestMapping("/shiro/article")
public class ArticleController {

    @GetMapping("/delete")
    @RequiresRoles(value = {"admin"})
    public String deleteArticle() {
        return "文章删除成功！";
    }

    @GetMapping("/read")
    @RequiresPermissions(value = {"article:read"})
    public String readArticle() {
        return "请您鉴赏！";
    }

    @GetMapping("/testSession")
    public String testSession() {
        Subject subject = SecurityUtils.getSubject();
        try {
            // 禁用session后无法调用getSession()
            Session session = subject.getSession();
        } catch (DisabledSessionException e) {
            return e.toString();
        }
//        session.setAttribute("MyName", "MySession--Ben");
        return subject.getPrincipal() == null ? "null" : subject.getPrincipal().toString();
    }
}



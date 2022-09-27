package com.knight.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
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
        Session session = subject.getSession();
        session.setAttribute("MyName", "MySession--Ben");
        return session.getAttribute("MyName").toString() + "::" + subject.getPrincipal().toString();
    }
}

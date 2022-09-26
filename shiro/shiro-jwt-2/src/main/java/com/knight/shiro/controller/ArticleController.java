package com.knight.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/delete")
    @RequiresRoles(value = {"admin"})
    public String deleteArticle(ModelMap model) {
        return "文章删除成功！";
    }

    @GetMapping("/read")
    @RequiresPermissions(value = {"article:read"})
    public String readArticle(ModelMap model) {
        return "请您鉴赏！";
    }
}

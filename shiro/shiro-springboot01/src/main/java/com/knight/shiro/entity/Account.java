package com.knight.shiro.entity;

import lombok.Data;

/**
 * @author TortoiseKnightB
 * @date 2022/08/20
 */
@Data
public class Account {

    private Integer id;
    private String username;
    private String password;
    private String perms;
    private String role;
}

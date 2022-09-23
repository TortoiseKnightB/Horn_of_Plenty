package com.knight.shiro.shirojwt.model;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author TortoiseKnightB
 * @date 2022/09/22
 */

public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

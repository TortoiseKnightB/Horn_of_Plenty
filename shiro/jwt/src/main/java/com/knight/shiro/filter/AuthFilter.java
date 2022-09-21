package com.knight.shiro.filter;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.knight.shiro.model.User;
import com.knight.shiro.util.JwtUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 验证拦截器，token在此处进行校验
 *
 * @author TortoiseKnightB
 * @date 2022/09/21
 */
@WebFilter(
        filterName = "authFilter",
        urlPatterns = "/jwt/*"
)
public class AuthFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String type = request.getParameter("type");
        if ("login".equals(type)) {
            chain.doFilter(request, response);
            return;
        }
        String token = request.getParameter("token");
        if (token == null) {
            System.out.println("未获取到token");
            return;
        }
        DecodedJWT decodedJWT = JwtUtil.verifyToken(token);
        User user = JwtUtil.parse(decodedJWT);
        System.out.println(user);

        chain.doFilter(request, response);
    }
}

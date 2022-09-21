package com.knight.shiro.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.knight.shiro.model.User;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

/**
 * JWT工具类
 *
 * @author TortoiseKnightB
 * @date 2022/09/20
 */
public class JwtUtil {

    private static final String key = "123456abc";

    /**
     * 生成token
     *
     * @param user
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String generateToken(User user) throws UnsupportedEncodingException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);

        JWTCreator.Builder builder = JWT.create()
                .withClaim("userInfo", JSON.toJSONString(user))
                .withExpiresAt(calendar.getTime());

        String token = builder.sign(Algorithm.HMAC256(key));
        return token;
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     * @throws UnsupportedEncodingException
     */
    public static DecodedJWT verifyToken(String token) throws UnsupportedEncodingException {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(key)).build().verify(token);
        return verify;
    }

    /**
     * 解析对象
     *
     * @param decodedJWT
     * @return
     */
    public static User parse(DecodedJWT decodedJWT) {
        String userInfo = decodedJWT.getClaim("userInfo").asString();
        if (userInfo != null) {
            User user = JSON.parseObject(userInfo, User.class);
            return user;
        }
        return null;
    }
}

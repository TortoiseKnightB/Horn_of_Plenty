package com.knight.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.knight.shiro.model.User;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

// https://jwt.io/introduction

/**
 * @author TortoiseKnightB
 * @date 2022/09/20
 */
class JwtApplicationTest {

    // hmac256摘要算法，与MD5类似，但是需要指定一个key（salt盐）
    String key = "123456abc";
    String charSet = "utf-8";

    @DisplayName("生成Token")
    @Test
    public void testGenerateToken() throws UnsupportedEncodingException {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);

        JWTCreator.Builder builder = JWT.create()
                // payload内容，由一个个claim组成
                .withClaim("userId", 123)
                .withClaim("userName", "Alen")
                .withClaim("url", "https://www.baidu.com")
                // 设置过期时间
                .withExpiresAt(calendar.getTime());

        // 签名Signature
        // hmac256摘要算法，与MD5类似，但是需要指定一个key（salt盐）
        String token = builder.sign(Algorithm.HMAC256(key));
        System.out.println(token);
        // xxxxx.yyyyy.zzzzz => Header.Payload.Signature
        // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6IkFsZW4iLCJleHAiOjE2NjM2NjAyODIsInVzZXJJZCI6MTIzLCJ1cmwiOiJodHRwOnd3dy5iYWlkdS5jb20ifQ.02xFtqMZiV7lHUnbmfY3TPAT2gBjxPRsk_cCp3UTta8
    }

    @DisplayName("校验")
    @Test
    public void testVerify() throws UnsupportedEncodingException {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6IkFsZW4iLCJleHAiOjE2NjM3NDk1NDQsInVzZXJJZCI6MTIzLCJ1cmwiOiJodHRwczovL3d3dy5iYWlkdS5jb20ifQ.HWmR0Zrxl2nje6cONIL2CL39jAxiP9wQ-K9c4AkWit4";
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(key)).build().verify(token);
        System.out.println(verify);
        if (verify != null) {
            // 获取payload里面的信息
            Integer userId = verify.getClaim("userId").asInt();
            String userName = verify.getClaim("userName").asString();
            String url = verify.getClaim("url").asString();
            System.out.println(userId);
            System.out.println(userName);
            System.out.println(url);
        }

    }

    @DisplayName("手写Jwt")
    @Test
    public void jwtSelf() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        // 获取Header
        JSONObject originalHeader = new JSONObject();
        originalHeader.put("alg", "HS256");
        originalHeader.put("type", "JWT");
        String header = Base64.encodeBase64URLSafeString(originalHeader.toJSONString().getBytes(charSet));
        // 获取Payload载荷
        JSONObject originalPayload = new JSONObject();
        originalPayload.put("userInfo", User.builder()
                .id(66)
                .name("Alen")
                .addr("上海")
                .build());
        String payload = Base64.encodeBase64URLSafeString(originalPayload.toJSONString().getBytes(charSet));
        // 获取Signature签名
        String signature = hmacSha256Encode(header, payload);
        String token = String.format("%s.%s.%s", header, payload, signature);
        System.out.println(token);

    }

    // jwt签名
    public String hmacSha256Encode(String header, String payload) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(charSet), "HmacSHA256");
        hmacSHA256.init(secretKeySpec);
        hmacSHA256.update(header.getBytes(charSet));
        hmacSHA256.update(".".getBytes(charSet));
        byte[] bytes = hmacSHA256.doFinal(payload.getBytes(charSet));
        return Base64.encodeBase64URLSafeString(bytes);
    }

}
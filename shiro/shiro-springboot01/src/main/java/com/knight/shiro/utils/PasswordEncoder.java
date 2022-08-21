package com.knight.shiro.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码加密
 *
 * @author TortoiseKnightB
 * @date 2022/08/21
 */
public class PasswordEncoder {

    public static String encoder(String password) {
        SimpleHash simpleHash = new SimpleHash(Md5Hash.ALGORITHM_NAME, ByteSource.Util.bytes(password), ByteSource.Util.bytes("salt"), 3);
        return simpleHash.toString();
    }
}

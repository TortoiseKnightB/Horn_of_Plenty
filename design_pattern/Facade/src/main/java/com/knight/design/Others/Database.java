package com.knight.design.Others;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 构成系统的其他角色，各自完成自己的工作，并不知道{@link com.knight.design.Facade.PageMaker Facade}角色的存在
 * <p>
 * 从邮件地址中获取用户名
 *
 * @author TortoiseKnightB
 * @date 2022/08/04
 */
public class Database {

    private Database() {
    }

    public static Properties getProperties(String dbname) {
        String fileName = dbname + ".txt";
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            System.out.println(fileName + " is not found");
        }
        return properties;
    }
}

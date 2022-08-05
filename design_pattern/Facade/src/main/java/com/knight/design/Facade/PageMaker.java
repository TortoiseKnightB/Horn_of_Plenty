package com.knight.design.Facade;

import com.knight.design.Others.Database;
import com.knight.design.Others.HtmlWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * 窗口，向系统外部提供高层接口
 * <p>
 * 根据邮件地址编写该用户的Web页面
 *
 * @author TortoiseKnightB
 * @date 2022/08/04
 */
public class PageMaker {

    private PageMaker() {
    }

    /**
     * 向系统外部提供的接口
     *
     * @param mailAddr
     * @param fileName
     */
    public static void makePage(String mailAddr, String fileName) {
        Properties maildata = Database.getProperties("design_pattern/Facade/src/main/java/com/knight/design/maildata");
        String name = maildata.getProperty(mailAddr);
        HtmlWriter htmlWriter = null;
        try {
            htmlWriter = new HtmlWriter(new FileWriter(fileName));
            htmlWriter.title("Welcome " + name);
            htmlWriter.mailTo(mailAddr, name);
            htmlWriter.close();
            System.out.println(fileName + " is created for " + mailAddr + " (" + name + ")");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

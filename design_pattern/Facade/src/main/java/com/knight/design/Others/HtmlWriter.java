package com.knight.design.Others;

import java.io.Writer;

/**
 * 构成系统的其他角色，各自完成自己的工作，并不知道{@link com.knight.design.Facade.PageMaker Facade}角色的存在
 * <p>
 * 编写HTML文件的类
 *
 * @author TortoiseKnightB
 * @date 2022/08/04
 */
public class HtmlWriter {

    private Writer writer;

    public HtmlWriter(Writer writer) {
        this.writer = writer;
    }

    public void title(String title) {
        System.out.println("title: " + title);
    }

    public void paragraph(String msg) {
        System.out.println("paragraph: " + msg);
    }

    public void link(String href, String caption) {
        System.out.println("link: " + href + " (" + caption + ")");
    }

    public void mailTo(String mailAddr, String username) {
        System.out.println(username + ": " + mailAddr);
    }

    public void close() {
        System.out.println("complete page");
    }
}

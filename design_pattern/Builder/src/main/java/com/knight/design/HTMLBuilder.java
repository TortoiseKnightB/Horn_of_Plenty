package com.knight.design;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ConcreteBuilder——具体的建造者，负责实现{@link Builder Builder}接口，还定义了获取最终生成结果的方法{@link #getResult()}
 * <p>
 * 使用HTML编写文档
 *
 * @author TortoiseKnightB
 * @date 2022/07/21
 */
public class HTMLBuilder extends Builder {

    private String filename;

    private PrintWriter writer;

    /**
     * 编写标题
     *
     * @param title
     */
    @Override
    public void makeTitle(String title) {
        filename = title + ".html";
        try {
            writer = new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println("<html><head><title>" + title + "</title></head><body>");
        writer.println("<h1>" + title + "</h1>");
    }

    /**
     * 编写字符串
     *
     * @param str
     */
    @Override
    public void makeString(String str) {
        writer.println("<p>" + str + "</p>");
    }

    /**
     * 编写条目
     *
     * @param items
     */
    @Override
    public void makeItems(String[] items) {
        writer.println("<ul>");
        for (String item : items) {
            writer.println("<li>" + item + "</li>");
        }
        writer.println("</ul>");
    }

    /**
     * 完成文档编写
     */
    @Override
    public void close() {
        writer.println("</body></html>");
        writer.close();
    }

    public String getResult() {
        return filename;
    }
}

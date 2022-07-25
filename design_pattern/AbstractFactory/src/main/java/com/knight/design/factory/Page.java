package com.knight.design.factory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * 抽象的产品
 *
 * @author TortoiseKnightB
 * @date 2022/07/22
 */
public abstract class Page {

    protected String title;
    protected String author;
    protected ArrayList<Item> content = new ArrayList<>();

    public Page(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void add(Item item) {
        content.add(item);
    }

    public void output() {
        try {
            String filename = title + ".html";
            Writer writer = new FileWriter(filename);
            writer.write(this.makeHTML());
            writer.close();
            System.out.println(filename + " 编写完成。");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String makeHTML();
}

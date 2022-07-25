package com.knight.design.listfactory;

import com.knight.design.factory.Factory;
import com.knight.design.factory.Link;
import com.knight.design.factory.Page;
import com.knight.design.factory.Tray;

/**
 * @author TortoiseKnightB
 * @date 2022/07/22
 */
public class ListFactory extends Factory {

    @Override
    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }

    @Override
    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}

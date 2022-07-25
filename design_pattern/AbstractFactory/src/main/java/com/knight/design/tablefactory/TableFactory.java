package com.knight.design.tablefactory;

import com.knight.design.factory.Factory;
import com.knight.design.factory.Link;
import com.knight.design.factory.Page;
import com.knight.design.factory.Tray;

/**
 * @author TortoiseKnightB
 * @date 2022/07/25
 */
public class TableFactory extends Factory {

    @Override
    public Link createLink(String caption, String url) {
        return new TableLink(caption, url);
    }

    @Override
    public Tray createTray(String caption) {
        return new TableTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new TablePage(title, author);
    }
}

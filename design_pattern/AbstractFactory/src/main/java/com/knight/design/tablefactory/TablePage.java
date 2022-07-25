package com.knight.design.tablefactory;

import com.knight.design.factory.Page;

/**
 * @author TortoiseKnightB
 * @date 2022/07/25
 */
public class TablePage extends Page {

    public TablePage(String title, String author) {
        super(title, author);
    }

    @Override
    public String makeHTML() {
        return "Table Page" + title + author;
    }
}

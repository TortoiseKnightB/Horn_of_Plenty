package com.knight.design.tablefactory;

import com.knight.design.factory.Link;

/**
 * @author TortoiseKnightB
 * @date 2022/07/25
 */
public class TableLink extends Link {

    public TableLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHTML() {
        return "Table Link" + caption + url;
    }
}

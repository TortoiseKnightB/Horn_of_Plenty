package com.knight.design.listfactory;

import com.knight.design.factory.Link;

/**
 * @author TortoiseKnightB
 * @date 2022/07/22
 */
public class ListLink extends Link {

    public ListLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHTML() {
        return "<li><a href=\"" + url + "\">" + caption + "</a></li>\n";
    }
}

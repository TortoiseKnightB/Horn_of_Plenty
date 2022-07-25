package com.knight.design.factory;

/**
 * 抽象的零件
 *
 * @author TortoiseKnightB
 * @date 2022/07/22
 */
public abstract class Link extends Item {

    protected String url;

    public Link(String caption, String url) {
        super(caption);
        this.url = url;
    }

}

package com.knight.design.factory;

/**
 * 抽象的零件
 *
 * @author TortoiseKnightB
 * @date 2022/07/22
 */
public abstract class Item {

    protected String caption;

    public Item(String caption) {
        this.caption = caption;
    }

    public abstract String makeHTML();
}

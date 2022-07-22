package com.knight.design;

/**
 * Director——建工，负责使用{@link Builder Builder}角色的接口来生成实例
 *
 * @author TortoiseKnightB
 * @date 2022/07/20
 */
public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.makeTitle("Greeting");
        builder.makeString(" Day to night ");
        builder.makeItems(new String[]{"Good night", "Good evening", "Goodbye"});
        builder.close();
    }
}

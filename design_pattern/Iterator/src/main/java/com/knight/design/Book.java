package com.knight.design;

/**
 * 书，需要被遍历的元素
 *
 * @author TortoiseKnightB
 * @date 2022/07/15
 */
public class Book {

    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

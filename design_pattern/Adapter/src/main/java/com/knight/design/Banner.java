package com.knight.design;

/**
 * @author TortoiseKnightB
 * @date 2022/07/15
 */
public class Banner {

    private String str;

    public Banner(String str) {
        this.str = str;
    }

    public void showWithParen() {
        System.out.println("(" + str + ")");
    }

    public void showWithAster() {
        System.out.println("*" + str + "*");
    }
}

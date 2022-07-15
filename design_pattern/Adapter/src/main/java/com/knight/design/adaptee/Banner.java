package com.knight.design.adaptee;

/**
 * 实际情况，底层实现的接口，被适配的角色
 *
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

package com.knight.design;

/**
 * 只存在一个实例的类
 *
 * @author TortoiseKnightB
 * @date 2022/07/19
 */
public class Singleton {

    /**
     * 初始化行为仅在该类被加载时进行一次
     */
    private static Singleton singleton = new Singleton();

    /**
     * private 表示禁止从该类外部调用构造函数
     */
    private Singleton() {
        System.out.println("生成了一个实例");
    }

    public static Singleton getInstance() {
        return singleton;
    }
}

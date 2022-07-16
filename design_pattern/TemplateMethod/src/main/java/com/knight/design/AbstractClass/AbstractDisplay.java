package com.knight.design.AbstractClass;

/**
 * 抽象类，包含调用抽象方法的模板方法
 *
 * @author TortoiseKnightB
 * @date 2022/07/16
 */
public abstract class AbstractDisplay {

    public abstract void open();

    public abstract void print();

    public abstract void close();

    /**
     * 模板方法，调用抽象方法
     * <p>
     * final方法表示该方法不能被子类的方法重写
     */
    public final void display() {
        open();
        for (int i = 0; i < 5; i++) {
            print();
        }
        close();
    }
}

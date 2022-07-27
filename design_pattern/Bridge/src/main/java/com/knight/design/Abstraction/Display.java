package com.knight.design.Abstraction;

import com.knight.design.Implementor.DisplayImpl;

/**
 * 抽象化，”类的功能结构层次“的最上层
 *
 * @author TortoiseKnightB
 * @date 2022/07/26
 */
public class Display {

    private DisplayImpl impl;

    public Display(DisplayImpl impl) {
        this.impl = impl;
    }

    public void open() {
        impl.rawOpen();
    }

    public void print() {
        impl.rawPrint();
    }

    public void close() {
        impl.rawClose();
    }

    public final void display() {
        open();
        print();
        close();
    }
}

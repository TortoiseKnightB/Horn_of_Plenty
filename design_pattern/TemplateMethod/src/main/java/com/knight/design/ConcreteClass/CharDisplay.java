package com.knight.design.ConcreteClass;

import com.knight.design.AbstractClass.AbstractDisplay;

/**
 * 具体类，具体实现AbstractClass中定义的抽象方法
 *
 * @author TortoiseKnightB
 * @date 2022/07/16
 */
public class CharDisplay extends AbstractDisplay {

    private char ch;

    public CharDisplay(char ch) {
        this.ch = ch;
    }

    @Override
    public void open() {
        System.out.print("<<");
    }

    @Override
    public void print() {
        System.out.print(ch);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }
}

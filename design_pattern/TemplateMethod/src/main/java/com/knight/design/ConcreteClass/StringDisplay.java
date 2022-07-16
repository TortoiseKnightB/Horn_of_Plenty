package com.knight.design.ConcreteClass;

import com.knight.design.AbstractClass.AbstractDisplay;

/**
 * 具体类，具体实现AbstractClass中定义的抽象方法
 *
 * @author TortoiseKnightB
 * @date 2022/07/16
 */
public class StringDisplay extends AbstractDisplay {

    private String string;

    private int width;

    public StringDisplay(String string) {
        this.string = string;
        this.width = string.getBytes().length;
    }

    @Override
    public void open() {
        printLine();
    }

    @Override
    public void print() {
        System.out.println("|" + string + "|");
    }

    @Override
    public void close() {
        printLine();
    }

    private void printLine() {
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}

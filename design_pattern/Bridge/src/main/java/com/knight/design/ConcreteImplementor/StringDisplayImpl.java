package com.knight.design.ConcreteImplementor;

import com.knight.design.Implementor.DisplayImpl;

/**
 * 具体实现者，负责实现在{@link DisplayImpl Implementor}角色中定义的接口
 *
 * @author TortoiseKnightB
 * @date 2022/07/26
 */
public class StringDisplayImpl extends DisplayImpl {

    private String string;

    private int width;

    public StringDisplayImpl(String string) {
        this.string = string;
        this.width = string.getBytes().length;
    }

    @Override
    public void rawOpen() {
        printLine();
    }

    @Override
    public void rawPrint() {
        System.out.println("|" + string + "|");
    }

    @Override
    public void rawClose() {
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

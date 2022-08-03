package com.knight.design.Component;

/**
 * 核心角色，定义接口，显示多行字符串
 *
 * @author TortoiseKnightB
 * @date 2022/08/02
 */
public abstract class Display {

    public abstract int getColumns();

    public abstract int getRows();

    public abstract String getRowText(int row);

    public final void show() {
        for (int i = 0; i < getRows(); i++) {
            System.out.println(getRowText(i));
        }
    }
}

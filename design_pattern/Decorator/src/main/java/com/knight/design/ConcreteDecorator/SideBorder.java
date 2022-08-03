package com.knight.design.ConcreteDecorator;

import com.knight.design.Component.Display;
import com.knight.design.Decorator.Border;

/**
 * 具体的Decorator角色，用指定的字符装饰字符的左右两侧
 *
 * @author TortoiseKnightB
 * @date 2022/08/02
 */
public class SideBorder extends Border {

    private char boderChar;

    public SideBorder(Display display, char boderChar) {
        super(display);
        this.boderChar = boderChar;
    }

    @Override
    public int getColumns() {
        return 1 + display.getColumns() + 1;
    }

    @Override
    public int getRows() {
        return display.getRows();
    }

    @Override
    public String getRowText(int row) {
        return boderChar + display.getRowText(row) + boderChar;
    }
}

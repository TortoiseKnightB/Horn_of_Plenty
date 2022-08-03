package com.knight.design.ConcreteComponent;

import com.knight.design.Component.Display;

/**
 * 实现{@link Display Component}所定义的接口，显示单行字符串
 *
 * @author TortoiseKnightB
 * @date 2022/08/02
 */
public class StringDisplay extends Display {

    private String string;

    public StringDisplay(String string) {
        this.string = string;
    }

    @Override
    public int getColumns() {
        return string.getBytes().length;
    }

    @Override
    public int getRows() {
        return 1;
    }

    @Override
    public String getRowText(int row) {
        if (row == 0) {
            return string;
        } else {
            return null;
        }
    }
}

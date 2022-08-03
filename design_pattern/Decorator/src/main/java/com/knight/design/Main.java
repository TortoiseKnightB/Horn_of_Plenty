package com.knight.design;

import com.knight.design.Component.Display;
import com.knight.design.ConcreteComponent.StringDisplay;
import com.knight.design.ConcreteDecorator.FullBorder;
import com.knight.design.ConcreteDecorator.SideBorder;

/**
 * @author TortoiseKnightB
 * @date 2022/08/02
 */
public class Main {
    public static void main(String[] args) {
        Display b1 = new StringDisplay("hello world");
        Display b2 = new SideBorder(b1, '#');
        Display b3 = new FullBorder(b2);
        b1.show();
        b2.show();
        b3.show();
        Display b4 = new SideBorder(
                new FullBorder(
                        new FullBorder(
                                new SideBorder(
                                        new FullBorder(
                                                new StringDisplay("HELLO WORLD")
                                        ),
                                        '*')
                        )
                ),
                '/');
        b4.show();
    }
}

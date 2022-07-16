package com.knight.design;

import com.knight.design.AbstractClass.AbstractDisplay;
import com.knight.design.ConcreteClass.CharDisplay;
import com.knight.design.ConcreteClass.StringDisplay;

/**
 * @author TortoiseKnightB
 * @date 2022/07/16
 */
public class Main {
    public static void main(String[] args) {
        AbstractDisplay d1 = new CharDisplay('H');
        AbstractDisplay d2 = new StringDisplay("hello world");
        AbstractDisplay d3 = new StringDisplay("你好，世界");
        d1.display();
        d2.display();
        d3.display();
    }
}

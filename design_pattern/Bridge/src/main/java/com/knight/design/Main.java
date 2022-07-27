package com.knight.design;

import com.knight.design.Abstraction.Display;
import com.knight.design.ConcreteImplementor.StringDisplayImpl;
import com.knight.design.RefinedAbstraction.CountDisplay;

/**
 * @author TortoiseKnightB
 * @date 2022/07/26
 */
public class Main {
    public static void main(String[] args) {
        Display d1 = new Display(new StringDisplayImpl("hello world"));
        Display d2 = new CountDisplay(new StringDisplayImpl("HELLO WORLD"));
        CountDisplay d3 = new CountDisplay(new StringDisplayImpl("hello universe"));
        d1.display();
        d2.display();
        d3.display();
        d3.multiDisplay(5);
    }
}

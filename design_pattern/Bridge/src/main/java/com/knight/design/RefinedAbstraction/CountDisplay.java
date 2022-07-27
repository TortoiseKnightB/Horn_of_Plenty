package com.knight.design.RefinedAbstraction;

import com.knight.design.Abstraction.Display;
import com.knight.design.Implementor.DisplayImpl;

/**
 * 改善后的抽象化，在{@link Display Abstraction}角色的基础上增加了新功能
 *
 * @author TortoiseKnightB
 * @date 2022/07/26
 */
public class CountDisplay extends Display {

    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }

    public void multiDisplay(int times) {
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}

package com.knight.design.Decorator;

import com.knight.design.Component.Display;
import com.knight.design.ConcreteComponent.StringDisplay;

/**
 * 装饰边框的抽象类，内部保存了被装饰的对象{@link Display Display}
 *
 * @author TortoiseKnightB
 * @date 2022/08/02
 */
public abstract class Border extends Display {

    /**
     * {@link StringDisplay StringDisplay}或{@link Border Border}类的子类
     */
    protected Display display;

    public Border(Display display) {
        this.display = display;
    }
}

package com.knight.design.ConcreteObserver;

import com.knight.design.Observer.Observer;
import com.knight.design.Subject.NumberGenerator;

/**
 * 具体的观察者，将观察到的数值以数字形式显示
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public class DigitObserver implements Observer {

    @Override
    public void update(NumberGenerator generator) {
        System.out.println("DigitObserver: " + generator.getNumber());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

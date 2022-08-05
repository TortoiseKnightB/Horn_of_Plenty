package com.knight.design.ConcreteObserver;

import com.knight.design.Observer.Observer;
import com.knight.design.Subject.NumberGenerator;


/**
 * 具体的观察者，将观察到的数值以简单图形显示
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public class GraphObserver implements Observer {

    @Override
    public void update(NumberGenerator generator) {
        System.out.print("GraphObserver: ");
        int count = generator.getNumber();
        for (int i = 0; i < count; i++) {
            System.out.print("*");
        }
        System.out.println();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

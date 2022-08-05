package com.knight.design;

import com.knight.design.ConcreteObserver.DigitObserver;
import com.knight.design.ConcreteObserver.GraphObserver;
import com.knight.design.ConcreteSubject.RandomNumberGenerator;
import com.knight.design.Observer.Observer;
import com.knight.design.Subject.NumberGenerator;

/**
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public class Main {
    public static void main(String[] args) {
        NumberGenerator generator = new RandomNumberGenerator();
        Observer o1 = new DigitObserver();
        Observer o2 = new GraphObserver();
        generator.addObserver(o1);
        generator.addObserver(o2);
        generator.execute();
    }
}

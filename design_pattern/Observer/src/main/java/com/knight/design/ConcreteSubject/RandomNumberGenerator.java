package com.knight.design.ConcreteSubject;

import com.knight.design.Observer.Observer;
import com.knight.design.Subject.NumberGenerator;

import java.util.Random;

/**
 * 具体的观察对象。当自身状态发生变化后，会通知所有已经注册的观察者{@link Observer Observer}
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public class RandomNumberGenerator extends NumberGenerator {

    private Random random = new Random();

    private int number;

    /**
     * 获取数值
     *
     * @return
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * 生成数值
     */
    @Override
    public void execute() {
        for (int i = 0; i < 10; i++) {
            number = random.nextInt(10);
            notifyObservers();
        }
    }

}

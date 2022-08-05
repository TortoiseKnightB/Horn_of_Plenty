package com.knight.design.Subject;

import com.knight.design.Observer.Observer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 观察对象，定义了注册观察者和删除观察者的方法
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public abstract class NumberGenerator {

    private ArrayList<Observer> observers = new ArrayList<>();

    /**
     * 注册观察者
     *
     * @param observer
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * 删除观察者
     *
     * @param observer
     */
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 向{@link Observer}发送通知
     */
    public void notifyObservers() {
        Iterator<Observer> iterator = observers.iterator();
        while (iterator.hasNext()) {
            Observer next = iterator.next();
            next.update(this);
        }
    }

    /**
     * 获取数值
     *
     * @return
     */
    public abstract int getNumber();

    /**
     * 生成数值
     */
    public abstract void execute();
}

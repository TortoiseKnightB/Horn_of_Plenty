package com.knight.design;

import com.knight.design.ConcreteHandler.LimitSupport;
import com.knight.design.ConcreteHandler.NoSupport;
import com.knight.design.ConcreteHandler.OddSupport;
import com.knight.design.ConcreteHandler.SpecialSupport;
import com.knight.design.Handler.Support;

/**
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public class Main {
    public static void main(String[] args) {
        Support alice = new NoSupport("Alice");
        Support bob = new LimitSupport("bob", 100);
        Support charlie = new SpecialSupport("Charlie", 429);
        Support diana = new LimitSupport("Diana", 200);
        Support elmo = new OddSupport("Elmo");
        Support fred = new LimitSupport("Fred", 300);
        alice.setNext(bob).setNext(charlie).setNext(diana).setNext(elmo).setNext(fred);
        for (int i = 0; i < 500; i += 33) {
            alice.support(new Trouble(i));
        }
    }
}

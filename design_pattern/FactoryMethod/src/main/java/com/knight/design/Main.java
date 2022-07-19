package com.knight.design;

import com.knight.design.framework.Factory;
import com.knight.design.framework.Product;
import com.knight.design.idcard.IDCardFactory;

/**
 * @author TortoiseKnightB
 * @date 2022/07/19
 */
public class Main {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("Ben");
        Product card2 = factory.create("Maxwell");
        Product card3 = factory.create("John");
        card1.use();
        card2.use();
        card3.use();
    }
}

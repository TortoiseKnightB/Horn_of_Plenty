package com.knight.design.concrete_prototype;

import com.knight.design.prototype.Product;

/**
 * 具体的原型
 *
 * @author TortoiseKnightB
 * @date 2022/07/19
 */
public class MessageBox implements Product {

    private char decoChar;

    public MessageBox(char decoChar) {
        this.decoChar = decoChar;
    }

    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decoChar);
        }
        System.out.println("");
        System.out.println(decoChar + " " + s + " " + decoChar);
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decoChar);
        }
        System.out.println("");
    }

    /**
     * 复制现有实例并生成新的实例
     *
     * @return
     */
    @Override
    public Product createClone() {
        Product p;
        try {
            p = (Product) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
}

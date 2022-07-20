package com.knight.design.concrete_prototype;

import com.knight.design.prototype.Product;

/**
 * 具体的原型
 *
 * @author TortoiseKnightB
 * @date 2022/07/20
 */
public class UnderlinePen implements Product {

    private char ulchar;

    public UnderlinePen(char ulchar) {
        this.ulchar = ulchar;
    }

    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        System.out.println("\"" + s + "\"");
        System.out.print(" ");
        for (int i = 0; i < length; i++) {
            System.out.print(ulchar);
        }
        System.out.println(" ");
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

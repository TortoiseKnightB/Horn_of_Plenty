package com.knight.design.prototype;

/**
 * 原型，定义克隆实例的方法
 *
 * @author TortoiseKnightB
 * @date 2022/07/19
 */
public interface Product extends Cloneable {

    void use(String s);

    /**
     * 复制现有实例并生成新的实例
     *
     * @return
     */
    Product createClone();
}

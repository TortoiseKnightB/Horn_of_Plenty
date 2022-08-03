package com.knight.design.Element;

import com.knight.design.Visitor.Visitor;

/**
 * Visitor角色的访问对象，声明了接受访问者{@link Visitor Visitor}访问的方法{@link #accept(Visitor)}
 *
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public interface Element {

    /**
     * 通过该方法，将对数据结构的处理独立出去，交给{@link Visitor}负责
     *
     * @param v
     */
    void accept(Visitor v);

}

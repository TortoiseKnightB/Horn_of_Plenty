package com.knight.design.ConcreteElement;

import com.knight.design.Entry;
import com.knight.design.Visitor.Visitor;

/**
 * 实现{@link com.knight.design.Element.Element Element}角色所定义的接口
 * <p>
 * 参考Composite模式
 *
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public class File extends Entry {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    /**
     * 通过该方法，将对数据结构的处理独立出去，交给{@link Visitor}负责
     *
     * @param v
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}

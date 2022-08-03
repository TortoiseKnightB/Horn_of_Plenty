package com.knight.design.ConcreteElement;

import com.knight.design.Entry;
import com.knight.design.Visitor.Visitor;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 实现{@link com.knight.design.Element.Element Element}角色所定义的接口
 * <p>
 * 对象结构（一人分饰两角），负责处理{@link com.knight.design.Element.Element Element}角色的集合
 * <p>
 * 参考Composite模式
 *
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public class Directory extends Entry {

    private String name;

    private ArrayList<Entry> directory = new ArrayList();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size = 0;
        Iterator<Entry> iterator = directory.iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            size += entry.getSize();
        }
        return size;
    }

    @Override
    public Entry add(Entry entry) {
        directory.add(entry);
        return this;
    }

    @Override
    public Iterator<Entry> iterator() {
        return directory.iterator();
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
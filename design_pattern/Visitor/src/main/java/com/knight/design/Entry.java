package com.knight.design;

import com.knight.design.Element.Element;

import java.util.Iterator;

/**
 * 参考Composite模式
 *
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public abstract class Entry implements Element {

    public abstract String getName();

    public abstract int getSize();

    public Entry add(Entry entry) throws FileTreatmenException {
        throw new FileTreatmenException();
    }

    public Iterator iterator() throws FileTreatmenException {
        throw new FileTreatmenException();
    }

    public String toString() {
        return getName() + "(" + getSize() + ")";
    }
}

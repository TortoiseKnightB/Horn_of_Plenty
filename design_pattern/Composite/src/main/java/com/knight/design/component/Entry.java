package com.knight.design.component;

import com.knight.design.FileTreatmenException;
import com.knight.design.composite.Directory;
import com.knight.design.leaf.File;

/**
 * 使{@link File 文件}和{@link Directory 文件夹}具有一致性的接口
 *
 * @author TortoiseKnightB
 * @date 2022/08/02
 */
public abstract class Entry {

    public abstract String getName();

    public abstract int getSize();

    public Entry add(Entry entry) throws FileTreatmenException {
        throw new FileTreatmenException();
    }

    public void printList() {
        printList("");
    }

    public abstract void printList(String prerfix);

    public String toString() {
        return getName() + "(" + getSize() + ")";
    }
}

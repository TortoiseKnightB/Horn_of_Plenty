package com.knight.design.leaf;

import com.knight.design.component.Entry;

/**
 * 文件，该角色中不能放入其他对象
 *
 * @author TortoiseKnightB
 * @date 2022/08/02
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

    @Override
    public void printList(String prerfix) {
        System.out.println(prerfix + "/" + this);
    }
}

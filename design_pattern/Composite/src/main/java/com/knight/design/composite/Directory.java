package com.knight.design.composite;

import com.knight.design.component.Entry;
import com.knight.design.FileTreatmenException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 文件夹，该角色中可放入文件、文件夹
 *
 * @author TortoiseKnightB
 * @date 2022/08/02
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
    public Entry add(Entry entry) throws FileTreatmenException {
        directory.add(entry);
        return this;
    }

    @Override
    public void printList(String prerfix) {
        System.out.println(prerfix + "/" + this);
        Iterator<Entry> iterator = directory.iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            entry.printList(prerfix + "/" + name);
        }
    }
}

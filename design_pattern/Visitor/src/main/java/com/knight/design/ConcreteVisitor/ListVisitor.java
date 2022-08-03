package com.knight.design.ConcreteVisitor;

import com.knight.design.ConcreteElement.Directory;
import com.knight.design.Entry;
import com.knight.design.ConcreteElement.File;
import com.knight.design.Visitor.Visitor;

import java.util.Iterator;

/**
 * 具体的访问者，处理数据结构
 *
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public class ListVisitor extends Visitor {

    private String currentdir = "";

    @Override
    public void visit(File file) {
        System.out.println(currentdir + "/" + file);
    }

    @Override
    public void visit(Directory directory) {
        System.out.println(currentdir + "/" + directory);
        String savedir = currentdir;
        currentdir = currentdir + "/" + directory.getName();
        Iterator<Entry> iterator = directory.iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            entry.accept(this);
        }
        currentdir = savedir;
    }
}

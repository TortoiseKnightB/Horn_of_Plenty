package com.knight.design;

import com.knight.design.ConcreteElement.Directory;
import com.knight.design.ConcreteElement.File;
import com.knight.design.ConcreteVisitor.ListVisitor;

/**
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        Directory bin = new Directory("bin");
        Directory tmp = new Directory("tmp");
        Directory usr = new Directory("usr");
        root.add(bin);
        root.add(tmp);
        root.add(usr);
        bin.add(new File("vi", 10000));
        bin.add(new File("latex", 20000));
        root.accept(new ListVisitor());
    }
}

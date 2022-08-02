package com.knight.design;

import com.knight.design.composite.Directory;
import com.knight.design.leaf.File;

/**
 * @author TortoiseKnightB
 * @date 2022/07/28
 */
public class Main {
    public static void main(String[] args) {
        try {
            Directory rootdir = new Directory("root");
            Directory bindir = new Directory("bin");
            Directory tmpdir = new Directory("tmp");
            Directory usrdir = new Directory("usr");
            rootdir.add(bindir);
            rootdir.add(tmpdir);
            rootdir.add(usrdir);
            bindir.add(new File("vi", 10000));
            bindir.add(new File("latex", 20000));
            rootdir.printList();
        } catch (FileTreatmenException e) {
            throw new RuntimeException(e);
        }
    }
}

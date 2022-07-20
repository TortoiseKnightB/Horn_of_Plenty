package com.knight.design;

import com.knight.design.client.Manager;
import com.knight.design.concrete_prototype.MessageBox;
import com.knight.design.concrete_prototype.UnderlinePen;
import com.knight.design.prototype.Product;

/**
 * @author TortoiseKnightB
 * @date 2022/07/19
 */
public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        UnderlinePen underlinePen = new UnderlinePen('~');
        MessageBox messageBox = new MessageBox('*');
        MessageBox messageBox1 = new MessageBox('/');
        manager.register("strong message", underlinePen);
        manager.register("warning box", messageBox);
        manager.register("slash box", messageBox1);

        Product p1 = manager.create("strong message");
        p1.use("hello world");
        Product p2 = manager.create("warning box");
        p2.use("helo world");
        Product p3 = manager.create("slash box");
        p3.use("hello world");
        Product p4 = manager.create("slash box");
        System.out.println(p3 == p4);

    }
}

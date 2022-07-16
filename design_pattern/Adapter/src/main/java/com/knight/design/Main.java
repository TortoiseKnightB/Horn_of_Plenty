package com.knight.design;

import com.knight.design.adapter.PrintBanner;
import com.knight.design.adapter.PrintBanner2;
import com.knight.design.tar.Print;
import com.knight.design.tar.Print2;

/**
 * @author TortoiseKnightB
 * @date 2022/07/15
 */
public class Main {
    public static void main(String[] args) {
        Print p = new PrintBanner("hello");
        p.printWeak();
        p.printStrong();

        Print2 p2 = new PrintBanner2("world");
        p2.printWeak();
        p2.printStrong();
    }
}

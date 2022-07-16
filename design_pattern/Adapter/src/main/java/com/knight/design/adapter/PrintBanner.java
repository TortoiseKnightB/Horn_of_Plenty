package com.knight.design.adapter;

import com.knight.design.adaptee.Banner;
import com.knight.design.tar.Print;

/**
 * 变换装置，类适配器，使用继承的适配器
 *
 * @author TortoiseKnightB
 * @date 2022/07/15
 */
public class PrintBanner extends Banner implements Print {

    public PrintBanner(String str) {
        super(str);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {
        showWithAster();
    }
}

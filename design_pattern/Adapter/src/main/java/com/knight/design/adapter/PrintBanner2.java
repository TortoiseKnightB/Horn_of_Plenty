package com.knight.design.adapter;

import com.knight.design.adaptee.Banner;
import com.knight.design.target.Print2;

/**
 * 变换装置，对象适配器，使用委托的适配器
 *
 * @author TortoiseKnightB
 * @date 2022/07/15
 */
public class PrintBanner2 extends Print2 {

    private Banner banner;

    public PrintBanner2(String str) {
        this.banner = new Banner(str);
    }

    @Override
    public void printWeak() {
        banner.showWithParen();
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}

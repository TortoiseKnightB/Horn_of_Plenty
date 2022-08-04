package com.knight.design;

/**
 * 发生问题
 *
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public class Trouble {

    /**
     * 问题编号
     */
    private int number;

    public Trouble(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return "[Trouble " + number + " ]";
    }
}



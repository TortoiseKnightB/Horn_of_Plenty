package com.knight.design;

/**
 * 表示猜拳游戏中”手势“的类
 *
 * @author TortoiseKnightB
 * @date 2022/07/27
 */
public class Hand {

    public static final int HANDVALUE_GUU = 0;  // 石头
    public static final int HANDVALUE_CHO = 1;  // 剪刀
    public static final int HANDVALUE_PAA = 2;  // 布
    public static final Hand[] hand = {
            new Hand(HANDVALUE_GUU),
            new Hand(HANDVALUE_CHO),
            new Hand(HANDVALUE_PAA)
    };

    private static final String[] name = {
            "石头", "剪刀", "布"
    };

    private int handValue;

    private Hand(int handValue) {
        this.handValue = handValue;
    }

    public static Hand getHand(int handValue) {
        return hand[handValue];
    }

    /**
     * this是否战胜了h
     *
     * @param h
     * @return
     */
    public boolean isStrongerThan(Hand h) {
        return fight(h) == 1;
    }

    /**
     * this是否败给了h
     *
     * @param h
     * @return
     */
    public boolean isWeakerThan(Hand h) {
        return fight(h) == -1;
    }

    /**
     * 计分：平_0，胜_1，负_-1
     *
     * @param h
     * @return
     */
    private int fight(Hand h) {
        if (this == h) {
            return 0;
        } else if ((this.handValue + 1) % 3 == h.handValue) {
            return 1;
        } else {
            return -1;
        }
    }

    public String toString() {
        return name[handValue];
    }
}

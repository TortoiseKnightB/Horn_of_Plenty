package com.knight.design.strategy;

import com.knight.design.Hand;

/**
 * 策略。定义了猜拳策略的抽象方法的接口
 *
 * @author TortoiseKnightB
 * @date 2022/07/27
 */
public interface Strategy {

    /**
     * 获取下一局要出的手势
     *
     * @return
     */
    Hand nextHand();

    /**
     * 学习手势
     *
     * @param win 上一局的手势是否获胜了
     */
    void study(boolean win);
}

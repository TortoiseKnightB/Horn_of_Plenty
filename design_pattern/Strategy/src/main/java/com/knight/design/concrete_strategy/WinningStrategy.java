package com.knight.design.concrete_strategy;

import com.knight.design.Hand;
import com.knight.design.strategy.Strategy;

import java.util.Random;

/**
 * 具体的策略，负责实现{@link Strategy Strategy}角色的接口
 * <p>
 * 策略1：如果上一局手势获胜，则继续沿用该手势，否则随机
 *
 * @author TortoiseKnightB
 * @date 2022/07/27
 */
public class WinningStrategy implements Strategy {

    private Random random;
    private boolean won = false;
    /**
     * 上一局出的手势
     */
    private Hand prevHand;

    public WinningStrategy(int seed) {
        random = new Random(seed);
    }

    /**
     * 获取下一局要出的手势
     *
     * @return
     */
    @Override
    public Hand nextHand() {
        if (!won) {
            prevHand = Hand.getHand(random.nextInt(3));
        }
        return prevHand;
    }

    /**
     * 学习手势
     *
     * @param win 上一局的手势是否获胜了
     */
    @Override
    public void study(boolean win) {
        won = win;
    }
}

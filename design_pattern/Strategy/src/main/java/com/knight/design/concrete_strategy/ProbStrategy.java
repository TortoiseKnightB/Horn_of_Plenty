package com.knight.design.concrete_strategy;

import com.knight.design.Hand;
import com.knight.design.strategy.Strategy;

import java.util.Random;

/**
 * 具体的策略，负责实现{@link Strategy Strategy}角色的接口
 * <p>
 * 策略2：随机出手势，概率根据历史猜拳结果改变
 *
 * @author TortoiseKnightB
 * @date 2022/07/27
 */
public class ProbStrategy implements Strategy {

    private Random random;
    /**
     * 上一局出的手势
     */
    private int prevHandValue = 0;
    /**
     * 这一局出的手势
     */
    private int currentHandValue = 0;
    /**
     * 记录过去的胜负，[{@link #prevHandValue prevHandValue}][{@link #currentHandValue currentHandValue}]
     */
    private int[][] history = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
    };

    public ProbStrategy(int seed) {
        random = new Random(seed);
    }


    /**
     * 获取下一局要出的手势
     * <p>
     * 根据当前手势出现的总局数，随机一个数，判断该数处在哪个手势的区间
     *
     * @return
     */
    @Override
    public Hand nextHand() {
        int bet = random.nextInt(getSum(currentHandValue));
        int handValue = 0;
        if (bet < history[currentHandValue][0]) {
            handValue = 0;
        } else if (bet < history[currentHandValue][0] + history[currentHandValue][1]) {
            handValue = 1;
        } else {
            handValue = 2;
        }
        prevHandValue = currentHandValue;
        currentHandValue = handValue;
        return Hand.getHand(handValue);
    }

    /**
     * 学习手势
     *
     * @param win 上一局的手势是否获胜了
     */
    @Override
    public void study(boolean win) {
        if (win) {
            history[prevHandValue][currentHandValue]++;
        } else {
            history[prevHandValue][(currentHandValue + 1) % 3]++;
            history[prevHandValue][(currentHandValue + 2) % 3]++;
        }
    }

    /**
     * 获取当前手势出现过的总局数
     *
     * @param hv
     * @return
     */
    private int getSum(int hv) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += history[hv][i];
        }
        return sum;
    }
}

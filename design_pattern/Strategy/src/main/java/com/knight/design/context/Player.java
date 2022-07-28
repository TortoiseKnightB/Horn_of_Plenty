package com.knight.design.context;

import com.knight.design.Hand;
import com.knight.design.strategy.Strategy;

/**
 * 上下文，负责使用{@link Strategy Strategy}角色，保存了具体策略角色（{@link com.knight.design.concrete_strategy.WinningStrategy WinningStrategy}、{@link com.knight.design.concrete_strategy.ProbStrategy ProbStrategy}）
 *
 * @author TortoiseKnightB
 * @date 2022/07/27
 */
public class Player {

    private String name;
    private Strategy strategy;
    private int wincount;
    private int losecount;
    private int gamecount;

    public Player(String name, Strategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    /**
     * 根据策略决定下一局要出的手势
     *
     * @return
     */
    public Hand nextHand() {
        return strategy.nextHand();
    }

    public void win() {
        strategy.study(true);
        wincount++;
        gamecount++;
    }

    public void lose() {
        strategy.study(false);
        losecount++;
        gamecount++;
    }

    public void even() {
        gamecount++;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", strategy=" + strategy +
                ", wincount=" + wincount +
                ", losecount=" + losecount +
                ", gamecount=" + gamecount +
                '}';
    }
}

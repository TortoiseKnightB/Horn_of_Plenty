package com.knight.design;


import com.knight.design.concrete_strategy.ProbStrategy;
import com.knight.design.concrete_strategy.WinningStrategy;
import com.knight.design.context.Player;

import java.util.Random;

/**
 * @author TortoiseKnightB
 * @date 2022/07/27
 */
public class Main {
    public static void main(String[] args) {
        int seed1 = new Random().nextInt();
        int seed2 = new Random().nextInt();
        Player player1 = new Player("Taro", new WinningStrategy(seed1));
        Player player2 = new Player("Hana", new ProbStrategy(seed2));

        for (int i = 0; i < 1000; i++) {
            Hand hand1 = player1.nextHand();
            Hand hand2 = player2.nextHand();
            if (hand1.isStrongerThan(hand2)) {
                System.out.println("Winner: Player1...");
                player1.win();
                player2.lose();
            } else if (hand2.isStrongerThan(hand1)) {
                System.out.println("Winner: Player2...");
                player1.lose();
                player2.win();
            } else {
                System.out.println("Even...");
                player1.even();
                player2.even();
            }
        }

        System.out.println("Total result:");
        System.out.println(player1);
        System.out.println(player2);
    }
}

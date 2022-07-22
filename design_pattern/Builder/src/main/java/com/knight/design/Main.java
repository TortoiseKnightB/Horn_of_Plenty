package com.knight.design;

/**
 * @author TortoiseKnightB
 * @date 2022/07/20
 */
public class Main {
    public static void main(String[] args) {

        TextBuilder textBuilder = new TextBuilder();
        Director director = new Director(textBuilder);
        director.construct();
        String result = textBuilder.getResult();
        System.out.println(result);

        HTMLBuilder htmlBuilder = new HTMLBuilder();
        Director director1 = new Director(htmlBuilder);
        director1.construct();
        String result1 = htmlBuilder.getResult();
        System.out.println(result1);


    }


}

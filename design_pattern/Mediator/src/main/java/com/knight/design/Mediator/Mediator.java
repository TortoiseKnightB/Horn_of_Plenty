package com.knight.design.Mediator;

/**
 * 仲裁者接口，负责定义与Colleague角色进行通信和做出决定的接口
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public interface Mediator {

    /**
     * 生成要管理的组员
     */
    void createColleagues();

    /**
     * 让组员向仲裁者报告
     */
    void colleagueChanged();
}

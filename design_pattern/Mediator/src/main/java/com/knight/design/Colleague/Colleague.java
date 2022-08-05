package com.knight.design.Colleague;

import com.knight.design.Mediator.Mediator;

/**
 * 组员接口，负责定义与Mediator进行通信的接口
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public interface Colleague {

    void setMediator(Mediator mediator);

    void setColleagueEnabled(boolean enabled);
}

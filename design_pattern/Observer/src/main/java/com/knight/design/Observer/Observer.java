package com.knight.design.Observer;

import com.knight.design.Subject.NumberGenerator;

/**
 * 观察者，接收来自{@link NumberGenerator Subject}角色的状态变化通知
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public interface Observer {

    /**
     * 接收来自状态变化通知
     *
     * @param generator
     */
    void update(NumberGenerator generator);
}

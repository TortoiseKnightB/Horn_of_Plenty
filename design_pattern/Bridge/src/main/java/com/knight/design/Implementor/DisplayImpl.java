package com.knight.design.Implementor;

import com.knight.design.Abstraction.Display;

/**
 * 实现者，”类的实现层次结构“的最上层，定义了用于实现{@link Display Abstraction}角色的接口的方法
 *
 * @author TortoiseKnightB
 * @date 2022/07/26
 */
public abstract class DisplayImpl {

    public abstract void rawOpen();

    public abstract void rawPrint();

    public abstract void rawClose();
}

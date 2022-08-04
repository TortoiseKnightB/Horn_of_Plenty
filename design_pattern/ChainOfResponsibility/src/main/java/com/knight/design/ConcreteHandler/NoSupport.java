package com.knight.design.ConcreteHandler;

import com.knight.design.Handler.Support;
import com.knight.design.Trouble;

/**
 * 解决问题的具体类——不处理问题
 *
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public class NoSupport extends Support {

    public NoSupport(String name) {
        super(name);
    }

    /**
     * 解决问题的办法
     *
     * @param trouble
     * @return
     */
    @Override
    protected boolean resolve(Trouble trouble) {
        return false;
    }
}

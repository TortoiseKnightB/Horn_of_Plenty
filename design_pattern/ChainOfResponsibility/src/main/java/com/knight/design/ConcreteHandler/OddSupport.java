package com.knight.design.ConcreteHandler;

import com.knight.design.Handler.Support;
import com.knight.design.Trouble;

/**
 * 解决问题的具体类——仅解决奇数编号的问题
 *
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public class OddSupport extends Support {

    public OddSupport(String name) {
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
        return trouble.getNumber() % 2 == 1;
    }
}

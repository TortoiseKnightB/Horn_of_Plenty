package com.knight.design.ConcreteHandler;

import com.knight.design.Handler.Support;
import com.knight.design.Trouble;

/**
 * 解决问题的具体类——仅解决编号小于指定编号的问题
 *
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public class LimitSupport extends Support {

    private int limit;

    public LimitSupport(String name, int limit) {
        super(name);
        this.limit = limit;
    }

    /**
     * 解决问题的办法
     *
     * @param trouble
     * @return
     */
    @Override
    protected boolean resolve(Trouble trouble) {
        return trouble.getNumber() < limit;
    }
}

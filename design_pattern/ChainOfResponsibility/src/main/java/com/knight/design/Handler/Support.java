package com.knight.design.Handler;

import com.knight.design.Trouble;

/**
 * 处理者，解决问题的抽象类，定义了处理请求的接口
 *
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public abstract class Support {

    private String name;

    private Support next;

    public Support(String name) {
        this.name = name;
    }

    public Support setNext(Support next) {
        this.next = next;
        return next;
    }

    /**
     * 解决问题的步骤
     *
     * @param trouble
     */
    public final void support(Trouble trouble) {
        if (resolve(trouble)) {
            done(trouble);
        } else if (next != null) {
            next.support(trouble);
        } else {
            fail(trouble);
        }
    }

    public String toString() {
        return "[" + name + "]";
    }

    /**
     * 解决问题的办法
     *
     * @param trouble
     * @return
     */
    protected abstract boolean resolve(Trouble trouble);

    protected void done(Trouble trouble) {
        System.out.println(trouble + " is resolved by " + this);
    }

    protected void fail(Trouble trouble) {
        System.out.println(trouble + " cannot be resolved");
    }
}

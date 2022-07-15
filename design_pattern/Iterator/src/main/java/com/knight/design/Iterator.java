package com.knight.design;

/**
 * 遍历集合的接口
 *
 * @author TortoiseKnightB
 * @date 2022/07/15
 */
public interface Iterator {

    /**
     * 判断是否存在下一个元素
     *
     * @return
     */
    public abstract boolean hasNext();

    /**
     * 获取下一个元素
     *
     * @return
     */
    public abstract Object next();
}

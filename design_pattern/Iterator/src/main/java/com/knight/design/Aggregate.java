package com.knight.design;

/**
 * 表示集合的接口
 *
 * @author TortoiseKnightB
 * @date 2022/07/15
 */
public interface Aggregate {

    /**
     * 生成一个用于遍历集合的迭代器
     *
     * @return
     */
    public abstract Iterator iterator();
}

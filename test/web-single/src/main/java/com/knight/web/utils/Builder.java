package com.knight.web.utils;

/**
 * 对象构建帮助接口
 *
 * @author TortoiseKnightB
 * @date 2022/08/29
 */
public interface Builder<T, V> {

    /**
     * 将对象V转换为对象T
     *
     * @param v
     * @return
     */
    T buildObject(V v);
}

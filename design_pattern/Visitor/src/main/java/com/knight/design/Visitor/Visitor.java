package com.knight.design.Visitor;

import com.knight.design.ConcreteElement.Directory;
import com.knight.design.ConcreteElement.File;

/**
 * 访问者，负责处理数据结构
 * <p>
 * 对数据结构中每个具体的元素{@link File File}、{@link Directory Directory}声明一个用于访问的方法visit
 *
 * @author TortoiseKnightB
 * @date 2022/08/03
 */
public abstract class Visitor {

    /**
     * 对File类型数据结构的处理方法
     *
     * @param file
     */
    public abstract void visit(File file);

    /**
     * 对Directory类型数据结构的处理方法
     *
     * @param directory
     */
    public abstract void visit(Directory directory);
}

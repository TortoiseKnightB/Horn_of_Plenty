package com.knight.design;

/**
 * Builder——建造者，定义用于生成实力的接口
 *
 * @author TortoiseKnightB
 * @date 2022/07/20
 */
public abstract class Builder {

    /**
     * 编写标题
     *
     * @param title
     */
    public abstract void makeTitle(String title);

    /**
     * 编写字符串
     *
     * @param str
     */
    public abstract void makeString(String str);

    /**
     * 编写条目
     *
     * @param items
     */
    public abstract void makeItems(String[] items);

    /**
     * 完成文档编写
     */
    public abstract void close();
}

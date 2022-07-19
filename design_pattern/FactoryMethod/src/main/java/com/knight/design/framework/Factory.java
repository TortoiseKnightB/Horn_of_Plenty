package com.knight.design.framework;

/**
 * 工厂，调用 {@link #create(String)} 生成产品实例（生成产品->注册产品）
 *
 * @author TortoiseKnightB
 * @date 2022/07/19
 */
public abstract class Factory {

    public final Product create(String owner) {
        Product p = createProduct(owner);
        registerProduct(p);
        return p;
    }

    /**
     * 生成产品
     * <p>
     * protected 表示可以被子类访问
     *
     * @param owner
     * @return
     */
    protected abstract Product createProduct(String owner);

    /**
     * 注册产品
     *
     * @param product
     */
    protected abstract void registerProduct(Product product);
}

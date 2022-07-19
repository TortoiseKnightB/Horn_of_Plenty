package com.knight.design.idcard;

import com.knight.design.framework.Factory;
import com.knight.design.framework.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TortoiseKnightB
 * @date 2022/07/19
 */
public class IDCardFactory extends Factory {

    private List<String> owners = new ArrayList<>();

    /**
     * 生成产品
     *
     * @param owner
     * @return
     */
    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    /**
     * 注册产品
     *
     * @param product
     */
    @Override
    protected void registerProduct(Product product) {
        owners.add(((IDCard) product).getOwner());
    }

    public List<String> getOwners() {
        return owners;
    }
}

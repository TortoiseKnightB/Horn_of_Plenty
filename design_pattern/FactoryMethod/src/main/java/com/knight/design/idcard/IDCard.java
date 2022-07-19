package com.knight.design.idcard;

import com.knight.design.framework.Product;

/**
 * @author TortoiseKnightB
 * @date 2022/07/19
 */
public class IDCard extends Product {

    private String owner;

    public IDCard(String owner) {
        System.out.println("制作" + owner + "的ID卡。");
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println("使用" + owner + "的ID卡。");
    }

    public String getOwner() {
        return owner;
    }
}

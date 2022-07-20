package com.knight.design.client;

import com.knight.design.prototype.Product;

import java.util.HashMap;

/**
 * 使用者
 *
 * @author TortoiseKnightB
 * @date 2022/07/19
 */
public class Manager {

    private HashMap<String, Product> showcase = new HashMap();

    public void register(String name, Product proto) {
        showcase.put(name, proto);
    }

    /**
     * 使用复制实例的方法生成新的实例
     * <p>
     * 此处没有使用类名，使用protoname代替，将框架从类名的束缚中解脱出来
     *
     * @param protoname
     * @return
     */
    public Product create(String protoname) {
        Product p = showcase.get(protoname);
        return p.createClone();
    }
}

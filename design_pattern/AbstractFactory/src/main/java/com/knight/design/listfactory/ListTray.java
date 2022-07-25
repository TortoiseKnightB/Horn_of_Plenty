package com.knight.design.listfactory;

import com.knight.design.factory.Item;
import com.knight.design.factory.Tray;

import java.util.Iterator;

/**
 * @author TortoiseKnightB
 * @date 2022/07/22
 */
public class ListTray extends Tray {

    public ListTray(String caption) {
        super(caption);
    }

    @Override
    public String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<li>\n");
        buffer.append(caption + "\n");
        buffer.append("<ul>\n");
        Iterator<Item> iterator = tray.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("</li>\n");
        return buffer.toString();
    }
}

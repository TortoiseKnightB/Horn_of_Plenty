package com.knight.design.tablefactory;

import com.knight.design.factory.Tray;

/**
 * @author TortoiseKnightB
 * @date 2022/07/25
 */
public class TableTray extends Tray {

    public TableTray(String caption) {
        super(caption);
    }

    @Override
    public String makeHTML() {
        return "Table Tray" + caption;
    }
}

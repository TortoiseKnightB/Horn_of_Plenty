package com.knight.design.ConcreteColleague;

import com.knight.design.Colleague.Colleague;
import com.knight.design.Mediator.Mediator;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 具体的组员
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public class ColleagueCheckBox extends Checkbox implements ItemListener, Colleague {

    private Mediator mediator;

    public ColleagueCheckBox(String caption, CheckboxGroup group, boolean state) {
        super(caption, group, state);
    }


    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnabled(boolean enabled) {
        setEnabled(enabled);
    }

    /**
     * 当状态发生变化是通知Mediator
     *
     * @param e
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        mediator.colleagueChanged();
    }
}

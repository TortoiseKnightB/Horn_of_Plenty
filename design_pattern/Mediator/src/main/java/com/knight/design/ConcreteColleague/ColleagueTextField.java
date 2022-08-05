package com.knight.design.ConcreteColleague;

import com.knight.design.Colleague.Colleague;
import com.knight.design.Mediator.Mediator;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

/**
 * 具体的组员
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public class ColleagueTextField extends TextField implements TextListener, Colleague {

    private Mediator mediator;

    public ColleagueTextField(String text, int columns) {
        super(text, columns);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnabled(boolean enabled) {
        setEnabled(enabled);
        setBackground(enabled ? Color.white : Color.lightGray);
    }

    /**
     * 当文字发生变化是通知Mediator
     *
     * @param e
     */
    @Override
    public void textValueChanged(TextEvent e) {
        mediator.colleagueChanged();
    }
}

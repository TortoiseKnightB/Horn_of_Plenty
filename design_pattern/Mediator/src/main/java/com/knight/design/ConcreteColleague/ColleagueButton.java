package com.knight.design.ConcreteColleague;

import com.knight.design.Colleague.Colleague;
import com.knight.design.Mediator.Mediator;

import java.awt.*;

/**
 * 具体的组员
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public class ColleagueButton extends Button implements Colleague {

    private Mediator mediator;

    public ColleagueButton(String caption) {
        super(caption);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnabled(boolean enabled) {
        setEnabled(enabled);
    }
}

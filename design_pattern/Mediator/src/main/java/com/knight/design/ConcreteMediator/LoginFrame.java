package com.knight.design.ConcreteMediator;

import com.knight.design.ConcreteColleague.ColleagueButton;
import com.knight.design.ConcreteColleague.ColleagueCheckBox;
import com.knight.design.ConcreteColleague.ColleagueTextField;
import com.knight.design.Mediator.Mediator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 具体的仲裁者，负责实际做出决定
 *
 * @author TortoiseKnightB
 * @date 2022/08/05
 */
public class LoginFrame extends Frame implements ActionListener, Mediator {

    private ColleagueCheckBox checkGuest;
    private ColleagueCheckBox checkLogin;
    private ColleagueTextField textUser;
    private ColleagueTextField textPass;
    private ColleagueButton buttonOK;
    private ColleagueButton buttonCancel;

    public LoginFrame(String title) {
        super(title);
        setBackground(Color.lightGray);
        setLayout(new GridLayout(4, 2));
        createColleagues();

        add(checkGuest);
        add(checkLogin);
        add(new Label("Username: "));
        add(textUser);
        add(new Label("Password: "));
        add(textPass);
        add(buttonOK);
        add(buttonCancel);

        colleagueChanged();

        pack();
        show();
    }

    /**
     * 生成要管理的组员
     */
    @Override
    public void createColleagues() {
        CheckboxGroup g = new CheckboxGroup();
        checkGuest = new ColleagueCheckBox("Guest", g, true);
        checkLogin = new ColleagueCheckBox("Login", g, false);
        textUser = new ColleagueTextField("", 10);
        textPass = new ColleagueTextField("", 10);
        textPass.setEchoChar('*');
        buttonOK = new ColleagueButton("OK");
        buttonCancel = new ColleagueButton("Cancel");

        checkGuest.setMediator(this);
        checkLogin.setMediator(this);
        textUser.setMediator(this);
        textPass.setMediator(this);
        buttonOK.setMediator(this);
        buttonCancel.setMediator(this);

        checkGuest.addItemListener(checkGuest);
        checkLogin.addItemListener(checkLogin);
        textUser.addTextListener(textUser);
        textPass.addTextListener(textPass);
        buttonOK.addActionListener(this);
        buttonCancel.addActionListener(this);
    }

    /**
     * 让组员向仲裁者报告
     */
    @Override
    public void colleagueChanged() {
        if (checkGuest.getState()) {
            // 游客模式
            textUser.setColleagueEnabled(false);
            textPass.setColleagueEnabled(false);
            buttonOK.setColleagueEnabled(true);
        } else {
            // 登录模式
            textUser.setColleagueEnabled(true);
            userpassChanged();
        }
    }

    /**
     * 当textUser或textPass文本输入框中的文字发生变化时，判断各Colleague的启用/禁用状态
     */
    private void userpassChanged() {
        if (textUser.getText().length() > 0) {
            textPass.setColleagueEnabled(true);
            if (textPass.getText().length() > 0) {
                buttonOK.setColleagueEnabled(true);
            } else {
                buttonOK.setColleagueEnabled(false);
            }
        } else {
            textPass.setColleagueEnabled(false);
            buttonOK.setColleagueEnabled(false);
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        System.exit(0);
    }
}

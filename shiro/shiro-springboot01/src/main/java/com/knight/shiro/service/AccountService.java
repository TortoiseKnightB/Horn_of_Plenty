package com.knight.shiro.service;

import com.knight.shiro.entity.Account;
import com.knight.shiro.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author TortoiseKnightB
 * @date 2022/08/20
 */
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public Account getAccount(String username) {
        return accountMapper.getAccount(username);
    }
}

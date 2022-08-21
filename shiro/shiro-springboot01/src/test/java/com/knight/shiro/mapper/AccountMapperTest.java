package com.knight.shiro.mapper;

import com.knight.shiro.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author TortoiseKnightB
 * @date 2022/08/20
 */
@Slf4j
@SpringBootTest
class AccountMapperTest {

    @Autowired
    AccountMapper accountMapper;

    @Test
    void getAccount() {
        Account account = accountMapper.getAccount("ww");
        log.info(account.toString());
    }
}
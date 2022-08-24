package com.knight.web.dao;

import com.knight.web.model.entity.UserInfoDO;
import com.knight.web.utils.JsonHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DataSource dataSource;

    @Test
    void getUserInfo() {
        System.out.println(dataSource.getClass());
        String name = "knight";
        UserInfoDO userInfo = userMapper.getUserInfo(name);
        System.out.println(JsonHelper.toJSON(userInfo));
    }
}
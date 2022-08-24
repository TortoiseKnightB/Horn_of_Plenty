package com.knight.web.service;

import com.knight.web.model.param.UserInfoParam;
import com.knight.web.model.response.UserInfoVO;
import com.knight.web.utils.JsonHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void getUserInfo() {
        UserInfoParam param = UserInfoParam.builder()
                .name("knight")
                .build();
        UserInfoVO userInfo = userService.getUserInfo(param);
        System.out.println(JsonHelper.toJSON(userInfo));
    }
}
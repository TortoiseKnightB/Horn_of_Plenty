package com.knight.web.service;

import com.knight.web.model.param.UserInfoListParam;
import com.knight.web.model.param.UserInfoParam;
import com.knight.web.model.response.PageBean;
import com.knight.web.model.response.UserInfoVO;
import com.knight.web.utils.JsonHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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

    @Test
    void getUserInfoList() {
        UserInfoListParam listParam = UserInfoListParam.builder()
                .pageNum(154)
                .pageSize(13)
                .build();
        PageBean<UserInfoVO> userInfoList = userService.getUserInfoList(listParam);
        if (userInfoList != null) {
            for (UserInfoVO infoVO : userInfoList.getData()) {
                System.out.println(JsonHelper.toJSON(infoVO));
            }
        }
    }
}
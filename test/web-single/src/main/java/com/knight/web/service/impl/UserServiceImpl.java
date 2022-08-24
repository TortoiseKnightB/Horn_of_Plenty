package com.knight.web.service.impl;

import com.knight.web.dao.UserMapper;
import com.knight.web.exception.CommonException;
import com.knight.web.model.entity.UserInfoDO;
import com.knight.web.model.enums.EnumCommonException;
import com.knight.web.model.param.UserInfoParam;
import com.knight.web.model.response.UserInfoVO;
import com.knight.web.service.UserService;
import com.knight.web.utils.ObjectBuildHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户信息
     *
     * @param param
     * @return
     */
    @Override
    public UserInfoVO getUserInfo(UserInfoParam param) {
        UserInfoDO userInfoDO = userMapper.getUserInfo(param.getName());
        if (userInfoDO == null) {
            throw new CommonException(EnumCommonException.DB_RESULT_ERROR, "查询用户不存在");
        }
        return ObjectBuildHelper.buildUserInfoVO(userInfoDO);
    }
}

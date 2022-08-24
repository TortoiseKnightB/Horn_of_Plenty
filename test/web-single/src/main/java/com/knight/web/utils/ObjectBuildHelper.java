package com.knight.web.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.knight.web.model.entity.UserInfoDO;
import com.knight.web.model.response.UserInfoVO;

/**
 * 对象构建帮助类
 *
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
public class ObjectBuildHelper {


    public static UserInfoVO buildUserInfoVO(UserInfoDO userInfoDO) {
        return UserInfoVO.builder()
                .id(userInfoDO.getId())
                .name(userInfoDO.getName())
                .password(userInfoDO.getPassword())
                .createTime(LocalDateTimeUtil.of(userInfoDO.getCreateTime()))
                .updateTime(LocalDateTimeUtil.of(userInfoDO.getUpdateTime()))
                .build();
    }
}

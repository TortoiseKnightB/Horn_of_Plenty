package com.knight.web.service;

import com.knight.web.model.param.UserInfoListParam;
import com.knight.web.model.param.UserInfoParam;
import com.knight.web.model.response.PageBean;
import com.knight.web.model.response.UserInfoVO;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
public interface UserService {

    /**
     * 获取用户信息
     *
     * @param param
     * @return
     */
    UserInfoVO getUserInfo(UserInfoParam param);

    /**
     * 分页获取用户信息列表
     *
     * @param param
     * @return
     */
    PageBean<UserInfoVO> getUserInfoList(UserInfoListParam param);
}

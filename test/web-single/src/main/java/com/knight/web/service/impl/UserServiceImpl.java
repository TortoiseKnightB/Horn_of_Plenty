package com.knight.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.knight.web.dao.UserMapper;
import com.knight.web.exception.CommonException;
import com.knight.web.model.entity.UserInfoDO;
import com.knight.web.model.enums.EnumCommonException;
import com.knight.web.model.param.PageParam;
import com.knight.web.model.param.UserInfoListParam;
import com.knight.web.model.param.UserInfoParam;
import com.knight.web.model.response.PageBean;
import com.knight.web.model.response.UserInfoVO;
import com.knight.web.service.UserService;
import com.knight.web.utils.Builder;
import com.knight.web.utils.ObjectBuildHelper;
import com.knight.web.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 分页获取用户信息列表
     *
     * @param param
     * @return
     */
    @Override
    public PageBean<UserInfoVO> getUserInfoList(UserInfoListParam param) {

        PageParam pageParam = PageUtil.checkPageParam(param.getPageNum(), param.getPageSize());

        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<UserInfoDO> userInfoList = userMapper.getUserInfoList();
        PageInfo<UserInfoDO> userInfoPageInfo = new PageInfo<>(userInfoList);

        PageBean<UserInfoVO> userInfoPageBean = PageUtil.buildPageBean(userInfoPageInfo, new Builder<List<UserInfoVO>, List<UserInfoDO>>() {
            @Override
            public List<UserInfoVO> buildObject(List<UserInfoDO> userInfoDOList) {
                return ObjectBuildHelper.buildUserInfoListVO(userInfoDOList);
            }
        });
        return userInfoPageBean;
    }
}

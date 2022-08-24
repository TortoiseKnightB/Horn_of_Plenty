package com.knight.web.dao;

import com.knight.web.model.entity.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Mapper
public interface UserMapper {

    UserInfoDO getUserInfo(@Param("name") String name);
}

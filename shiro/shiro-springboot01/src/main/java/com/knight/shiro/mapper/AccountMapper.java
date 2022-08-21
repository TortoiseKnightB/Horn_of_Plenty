package com.knight.shiro.mapper;

import com.knight.shiro.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author TortoiseKnightB
 * @date 2022/08/20
 */
@Mapper
public interface AccountMapper {

    Account getAccount(@Param("username") String username);
}

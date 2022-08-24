package com.knight.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 数据库实体类DO
 *
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDO {

    /**
     * 主键id
     */
    private int id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}

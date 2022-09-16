package com.knight.cache.rediscaffeinecache.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TortoiseKnightB
 * @date 2022/09/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {

    private String userName;

    private Integer age;
}

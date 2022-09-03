package com.knight.cache.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TortoiseKnightB
 * @date 2022/09/03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;

    private Integer age;
}

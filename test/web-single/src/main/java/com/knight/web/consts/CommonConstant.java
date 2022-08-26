package com.knight.web.consts;

import cn.hutool.core.net.NetUtil;

/**
 * 公共基础常量类
 *
 * @author TortoiseKnightB
 * @date 2022/08/26
 */
public class CommonConstant {

    private CommonConstant() {
    }

    /**
     * 本机IP
     */
    public static final String LOCALHOST = String.valueOf(NetUtil.getLocalhost());
}

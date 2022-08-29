package com.knight.web.controller;

import com.knight.web.model.param.UserInfoListParam;
import com.knight.web.model.param.UserInfoParam;
import com.knight.web.model.response.PageBean;
import com.knight.web.model.response.ResultInfo;
import com.knight.web.model.response.UserInfoVO;
import com.knight.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * http://127.0.0.1:8080/user
     *
     * @return
     */
    @GetMapping
    public ResultInfo<String> home() {
        return new ResultInfo<String>().succeed("api/user success");
    }

    @ApiOperation(value = "查询用户信息")
    @PostMapping("/getUserInfo")
    public ResultInfo<UserInfoVO> getUserInfo(@RequestBody UserInfoParam param) {
        return new ResultInfo<UserInfoVO>().succeed(userService.getUserInfo(param));
    }

    @ApiOperation(value = "分页查询用户信息列表")
    @PostMapping("/getUserInfoList")
    public ResultInfo<PageBean<UserInfoVO>> getUserInfoList(@RequestBody UserInfoListParam param) {
        return new ResultInfo<PageBean<UserInfoVO>>().succeed(userService.getUserInfoList(param));
    }


}

package com.knight.web.controller;

import com.knight.web.model.response.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TortoiseKnightB
 * @date 2022/08/25
 */
@Slf4j
@Api(tags = "其他测试接口")
@RestController
@RequestMapping(value = "/test")
public class TestController {


    @ApiOperation(value = "日志打印测试")
    @GetMapping("/logTest")
    public ResultInfo<String> logTest() {
        return new ResultInfo<String>().succeed("log test");
    }
}

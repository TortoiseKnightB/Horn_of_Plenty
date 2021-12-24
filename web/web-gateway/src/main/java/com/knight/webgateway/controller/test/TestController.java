package com.knight.webgateway.controller.test;

import com.knight.webgateway.model.param.TestParam;
import com.knight.webgateway.model.response.ResultInfo;
import com.knight.webgateway.model.response.vo.TestEntityVO;
import com.knight.webgateway.service.test.TestService;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author TortoiseKnightB
 * @date 2021/12/09
 */
@Api(tags = "测试controller")
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private TestService testService;

    // @NotNull Param param
    @ApiOperation(value = "测试controller：根据test_id查询数据库信息")
    @PostMapping(value = "/GetTestDataById")
    public ResultInfo<TestEntityVO> getTestDataById(@RequestBody TestParam param) {
        return new ResultInfo<TestEntityVO>().succeed(testService.startTestService(param.getId()));
    }

}

package com.knight.webgateway.controller.test;

import com.knight.gatewaycommon.model.response.ResultInfo;
import com.knight.webcommon.aspect.annotation.ExceptionHandlerAnnotation;
import com.knight.webcommon.aspect.annotation.NotBlank;
import com.knight.webcommon.aspect.annotation.NotNullAnnotation;
import com.knight.webcommon.aspect.annotation.TestAnnotation;
import com.knight.webgateway.model.param.SchoolParam;
import com.knight.webgateway.model.param.TestParam;
import com.knight.webgateway.model.response.vo.TestEntityVO;
import com.knight.webgateway.service.test.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author TortoiseKnightB
 * @date 2021/12/09
 */
@Api(tags = "测试controller")
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource(name = "TestService-gateway")
    private TestService testService;


    /**
     * 测试样例：api/test
     *
     * @return 若成功启动，则返回成功信息
     */
    @GetMapping
    public String test() {
        return "test succeed";
    }

    /**
     * 数据库测试样例：api/test/GetTestDataById
     *
     * @param param 数据库测试入参
     * @return 数据库查询信息
     */
    // @NotNull Param param
    @ApiOperation(value = "测试controller：根据test_id查询数据库信息")
    @PostMapping(value = "/GetTestDataById")
    public ResultInfo<TestEntityVO> getTestDataById(@RequestBody TestParam param) {
        return new ResultInfo<TestEntityVO>().succeed(testService.startTestService(param.getId()));
    }

    /**
     * 数据库测试样例： api/test/AddTestData
     *
     * @param param 数据库测试入参
     * @return 修改数据库是否成功
     */
    @ApiOperation(value = "测试controller：新增数据库信息")
    @PostMapping(value = "/AddTestData")
    public ResultInfo<Boolean> addTestData(@RequestBody TestParam param) {
        return new ResultInfo<Boolean>().succeed(testService.addTestData(param.getId(), param.getMessage()));
    }

    /**
     * TestAnnotation 注解测试样例：api/test/TestAspect
     *
     * @return
     */
    @PostMapping(value = "/TestAspect")
    @TestAnnotation
    public String testAspect() {
        System.out.println("test TestAnnotation");
        return "test @TestAnnotation succeed";
    }

    /**
     * NotNullAnnotation 注解测试样例：api/test/TestAspect2
     *
     * @return 注意观察参数为空时的输出信息
     */
    @PostMapping(value = "/TestAspect2")
    @NotNullAnnotation
    public String testAspect2(@RequestBody SchoolParam param) {
        System.out.println("test NotNullAnnotation");
        System.out.println(param);
        return "test @NotNullAnnotation succeed";
    }

    /**
     * ExceptionHandlerAnnotation 注解测试样例：api/test/TestAspect3
     *
     * @param param
     * @return
     */
    @PostMapping("/TestAspect3")
    @ExceptionHandlerAnnotation
    public ResultInfo<String> testAspect3(@RequestBody SchoolParam param) {
        System.out.println("test ExceptionHandlerAnnotation");
//        int a = 10 / 0;
        return new ResultInfo<String>().succeed(param.toString());
    }

}

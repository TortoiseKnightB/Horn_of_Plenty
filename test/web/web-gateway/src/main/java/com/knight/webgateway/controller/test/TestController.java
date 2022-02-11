package com.knight.webgateway.controller.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.knight.gatewaycommon.model.response.ResultInfo;
import com.knight.gatewaycommon.utils.JsonHelper;
import com.knight.webcommon.aspect.annotation.*;
import com.knight.webgateway.model.param.DateParam;
import com.knight.webgateway.model.param.SchoolParam;
import com.knight.webgateway.model.param.TestParam;
import com.knight.webgateway.model.response.vo.TestEntityVO;
import com.knight.webgateway.service.test.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.TimeZone;

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
    public ResultInfo<SchoolParam> testAspect3(@RequestBody SchoolParam param) {
        System.out.println("test ExceptionHandlerAnnotation");
        int a = 10 / 0;
        return new ResultInfo<SchoolParam>().succeed(param);
    }

    /**
     * LoggerAnnotation 注解测试样例：api/test/TestAspect4
     *
     * @param param
     * @return
     */
    @PostMapping("/TestAspect4")
    @LoggerAnnotation
    public ResultInfo<String> testAspect4(@RequestBody String param) {
        System.out.println("test");
//        int a = 10 / 0;
        return new ResultInfo<String>().succeed(param);
    }

    // TODO 将测试用例按模块分开

    /**
     * JsonHelper 工具类测试样例：api/test/TestUtils1
     *
     * @param param
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/TestUtils1")
    public TestParam testUtils1(@RequestBody TestParam param) throws JsonProcessingException {
        System.out.println(param);
        String json = JsonHelper.toJSON(param);
        System.out.println(json);
        TestParam object = JsonHelper.toObject(json, TestParam.class);
        System.out.println(object);
        return object;
    }

    /**
     * 时间格式测试样例：api/test/TestDate1
     *
     * @param dateParam
     * @return
     */
    @PostMapping("/TestDate1")
    public DateParam testDate1(@RequestBody DateParam dateParam) {
        System.out.println("本地时间: " + LocalDateTime.now());
        System.out.println("本地时区: " + TimeZone.getDefault());
        System.out.println("param: " + dateParam);
        System.out.println("Data: " + dateParam.getDate().toString());
        System.out.println("DataTime: " + dateParam.getDateTime().toString());
        System.out.println("LocalData: " + dateParam.getLocalDate().toString());
        System.out.println("LocalDataTime: " + dateParam.getLocalDateTime().toString());
        System.out.println("-------------------------------------------");
        String json = JsonHelper.toJSON(dateParam);
        System.out.println("toJSON: " + json);
        DateParam object = JsonHelper.toObject(json, DateParam.class);
        System.out.println("toObject: " + object);
        return object;
    }

}

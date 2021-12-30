package com.knight.webtest.api;

import com.knight.webcommon.api.TestApi;
import com.knight.webcommon.model.entity.TestData;
import com.knight.webcommon.model.entity.TestEntityDTO;
import com.knight.webcommon.model.response.ResultInfo;
import com.knight.webcommon.service.TestService;
import com.knight.webtest.utils.BuildHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * 测试 api 实现
 */
@Component
public class TestApiImpl implements TestApi {

    @Resource(name = "TestService-mid")
    private TestService testService;

    @GetMapping(value = "startTestApi")
    @Override
    public ResultInfo<TestEntityDTO> startTestApi(String id) {
        return new ResultInfo<TestEntityDTO>().succeed(testService.startTestService(id));
    }

    /**
     * 新增数据测试接口
     *
     * @param id      数据编号
     * @param message 数据信息
     * @return
     */
    @Override
    public ResultInfo<Boolean> addTestData(String id, String message) {
        TestData testData = BuildHelper.build(id, message);
        try {
            testService.addTestData(testData);
        } catch (Exception e) {
//            e.printStackTrace();
            // TODO 这里应该抛出异常，先用下面一条语句替换
            System.out.println("报异常了，快看！！");
            return new ResultInfo<Boolean>().succeed(false);
        }
        // TODO 通过读取数据库来判断是否插入成功
        return new ResultInfo<Boolean>().succeed(true);
    }

}

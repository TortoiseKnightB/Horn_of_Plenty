package com.knight.webgateway.service.test;

import com.knight.webgateway.model.response.vo.TestEntityVO;

/**
 * 这部分本应该抽取出去，作为外部小模块的特有数据，如：TestCommon，然后被 gateway 主模块引入
 * <p>
 * 但是目前这里仅作简单实现
 *
 * @author TortoiseKnightB
 * @date 2021/12/24
 */
public interface TestService {

    /**
     * 网关接口测试 Service
     *
     * @param id 数据库 test_id
     * @return TestEntityVO
     */
    TestEntityVO startTestService(String id);

    /**
     * 新增测试数据
     *
     * @param id      测试数据编号
     * @param message 测试数据信息
     * @return Boolean
     */
    Boolean addTestData(String id, String message);
}

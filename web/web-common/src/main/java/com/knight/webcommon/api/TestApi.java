package com.knight.webcommon.api;

import com.knight.webcommon.model.entity.TestEntityDTO;
import com.knight.webcommon.model.response.ResultInfo;

/**
 * 测试Api，中台对外接口，通过rpc方式发布出去
 * <p>
 * 或者通过 http 调用也行
 *
 * @author yang.liu22@outlook.com
 * @date 2021/12/09
 */
public interface TestApi {

    /**
     * api 测试接口
     *
     * @param id 数据库 test_id
     * @return
     */
    ResultInfo<TestEntityDTO> startTestApi(String id);

    /**
     * 新增数据测试接口
     *
     * @param id      数据编号
     * @param message 数据信息
     * @return
     */
    ResultInfo<Boolean> addTestData(String id, String message);

}

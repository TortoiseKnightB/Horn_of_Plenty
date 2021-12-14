package com.knight.webcommon.api;

/**
 * 测试Api，中台对外接口，通过rpc方式发布出去
 * <p>
 * 或者通过 http 调用也行
 *
 * @author yang.liu22@outlook.com
 * @date 2021/12/09
 */
public interface TestApi {

    String startTestApi(String id);

}

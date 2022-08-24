package com.knight.webgateway.model.param;

import com.knight.webcommon.aspect.annotation.NonBlank;
import com.knight.webcommon.model.param.BaseParam;
import lombok.Data;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@Data
public class TestAspect6Param extends BaseParam {

    @NonBlank(message = "name不能为空")
    private String name;

    @NonBlank(value = "param不能为空")
    private TestParam param;
}

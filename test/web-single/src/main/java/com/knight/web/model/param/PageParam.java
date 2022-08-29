package com.knight.web.model.param;

import com.knight.web.model.response.PageBean;
import com.knight.web.utils.PageUtil;
import lombok.Builder;
import lombok.Data;

/**
 * 分页参数
 *
 * @author TortoiseKnightB
 * @date 2022/08/29
 * @see PageBean
 * @see PageUtil
 */
@Data
@Builder
public class PageParam {

    /**
     * 每页的数量
     */
    private int pageSize;
    /**
     * 当前页
     */
    private int pageNum;
}

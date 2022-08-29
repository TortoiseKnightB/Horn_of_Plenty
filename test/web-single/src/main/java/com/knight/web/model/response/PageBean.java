package com.knight.web.model.response;

import com.knight.web.model.param.PageParam;
import com.knight.web.utils.PageUtil;
import lombok.Data;

import java.util.List;

/**
 * 分页结果封装类
 *
 * @author TortoiseKnightB
 * @date 2022/08/29
 * @see PageUtil
 * @see PageParam
 */
@Data
public class PageBean<T> {

    /**
     * 总页数
     */
    private int pages;
    /**
     * 每页的数量
     */
    private int pageSize;
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 当前页的数量
     */
    private int size;
    /**
     * 总记录数
     */
    protected long total;
    /**
     * 结果集
     */
    protected List<T> data;

}

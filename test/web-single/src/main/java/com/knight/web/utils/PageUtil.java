package com.knight.web.utils;

import com.github.pagehelper.PageInfo;
import com.knight.web.model.param.PageParam;
import com.knight.web.model.response.PageBean;

import java.util.List;

/**
 * 分页工具类
 *
 * @author TortoiseKnightB
 * @date 2022/08/29
 * @see PageParam
 * @see PageBean
 */
public class PageUtil {

    /**
     * 构建自定义分页对象
     *
     * @param pageInfo 分页器自带的分页信息对象
     * @param builder  对象构建帮助接口，自定义对象构建方法
     * @param <T>      构建后的对象类型
     * @param <V>      构建前的对象类型
     * @return
     */
    public static <T, V> PageBean<T> buildPageBean(PageInfo<V> pageInfo, Builder<List<T>, List<V>> builder) {
        if (pageInfo == null) {
            return null;
        }
        PageBean<T> pageBean = new PageBean<>();
        pageBean.setPages(pageInfo.getPages());
        pageBean.setPageSize(pageInfo.getPageSize());
        pageBean.setPageNum(pageInfo.getPageNum());
        pageBean.setSize(pageInfo.getSize());
        pageBean.setTotal(pageInfo.getTotal());
        pageBean.setData(builder.buildObject(pageInfo.getList()));
        return pageBean;
    }


    /**
     * 校验分页参数，如果参数错误则赋默认值（当前页码1，每页大小20条记录）
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return
     */
    public static PageParam checkPageParam(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 20;
        }
        return PageParam.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
    }
}

package com.knight.web.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.knight.web.model.entity.UserInfoDO;
import com.knight.web.utils.JsonHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author TortoiseKnightB
 * @date 2022/08/24
 */
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DataSource dataSource;

    @Test
    void getUserInfo() {
        System.out.println(dataSource.getClass());
        String name = "knight";
        UserInfoDO userInfo = userMapper.getUserInfo(name);
        System.out.println(JsonHelper.toJSON(userInfo));
    }

//    @DisplayName("分页测试前插入数据")
//    @Test
//    void insertUserInfoList() {
//        List<UserInfoDO> list = new ArrayList<>();
//        for (int i = 6; i < 2000; i++) {
//            list.add(UserInfoDO.builder()
//                    .name("Name_" + i)
//                    .password("password")
//                    .build());
//        }
//        userMapper.insertUserInfoList(list);
//        System.out.println("批量插入完毕");
//    }

    @DisplayName("分页测试")
    @Test
    void getUserInfoList() {
        int pageNum = 154;
        int pageSize = 13;

        // 紧跟它的查询就是分页查询(页码，页大小)
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfoDO> userInfoList = userMapper.getUserInfoList();

        for (int i = 0; i < Math.min(pageSize, userInfoList.size()); i++) {
            System.out.println(i + 1 + "：" + JsonHelper.toJSON(userInfoList.get(i)));
        }

        // 将查询到的结果放入PageInfo中，扩展其属性（第二个参数可选，表示分页栏下面一共展示多少页码，如下面为6页）
        // 首页 上一页 2 3 4 5 6 7 下一页 末页
        PageInfo<UserInfoDO> page = new PageInfo<>(userInfoList, 5);
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("总页码：" + page.getPages());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("当前页有几条记录：" + page.getSize());
        System.out.println("每页有几条记录：" + page.getPageSize());
        System.out.println("前一页：" + page.getPrePage());
        System.out.println("下一页：" + page.getNextPage());
        System.out.println("当前页面第一个元素在数据库中的行号：" + page.getStartRow());
        System.out.println("当前页面最后一个元素在数据库中的行号：" + page.getEndRow());
        System.out.println("是否有前一页：" + page.isHasPreviousPage());
        System.out.println("是否有下一页：" + page.isHasNextPage());
        System.out.println("查询结果：" + page.getList());
        System.out.println("所有导航页号：");
        for (int i : page.getNavigatepageNums()) {
            System.out.println(i);
        }
    }
}
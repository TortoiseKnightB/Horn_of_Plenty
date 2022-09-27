package com.knight.shiro.factory;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 定义一个 WebSubjectFactory, 用来禁用 session，使每次请求都是无状态请求，完全依赖JWT来进行验证
 * <p>
 * 禁用session后不能使用subject.getSession()，authc中也会自动调用subject.getSession()
 *
 * @author TortoiseKnightB
 * @date 2022/09/27
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
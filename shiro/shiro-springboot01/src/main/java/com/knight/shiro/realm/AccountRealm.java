package com.knight.shiro.realm;

import com.knight.shiro.entity.Account;
import com.knight.shiro.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author TortoiseKnightB
 * @date 2022/08/20
 */
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    AccountService accountService;

    /**
     * 授权模块
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        //设置角色
        Set<String> roles = new HashSet<>();
        roles.add(account.getRole());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);

        //设置权限
        authorizationInfo.addStringPermission(account.getPerms());
        return authorizationInfo;
    }

    /**
     * 认证模块
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        // 关键步骤，从这里读取数据库中的密码
        Account account = accountService.getAccount(usernamePasswordToken.getUsername());
        if (account != null) {
            // 给加密方式加盐
            ByteSource salt = ByteSource.Util.bytes("salt");
            // 这里将设置subject的principal
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account, account.getPassword(), salt, getName());
            return authenticationInfo;
        }
        return null;
    }
}





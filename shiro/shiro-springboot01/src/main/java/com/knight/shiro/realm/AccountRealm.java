package com.knight.shiro.realm;

import com.knight.shiro.entity.Account;
import com.knight.shiro.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
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
            // 这里将设置subject的principal，并验证密码（密码的比对由shiro自动完成）
            // 根据用户的情况，来构建 AuthenticationInfo 并返回，通常使用的实现类为 SimpleAuthenticationInfo
            // principal：认证的实体信息，可以是username，也可以是对应的实体类对象
            Object principal = account;
            // credentials：密码
            Object credentials = account.getPassword();
            // realmName：当前 realm 对象的 name，调用父类的getName()方法即可
            String realmName = getName();
            // salt：给加密方式加盐(即便两个人密码一样，加盐后也不一样)
            ByteSource salt = ByteSource.Util.bytes("salt");
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials, salt, realmName);
            return authenticationInfo;
        }
        return null;
    }


    // 使用 new SimpleHash(algorithmName, source, salt, hashIterations) 来计算盐值加密后的密码的值
    private String salt(String algorithmName, Object source, Object salt, int hashIterations) {
        SimpleHash hash = new SimpleHash(algorithmName, source, salt, hashIterations);
        return hash.toString();
    }
}





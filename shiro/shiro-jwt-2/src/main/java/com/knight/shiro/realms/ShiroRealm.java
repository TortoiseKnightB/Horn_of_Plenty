package com.knight.shiro.realms;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.knight.shiro.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * {@link #supports(AuthenticationToken)}限定该realm只处理UsernamePasswordToken，即只用来登录和授权。Jwt拦截由{@link JwtRealm}实现
 * <p>
 * 同时开启身份验证和权限验证，需要继承 AuthorizingRealm
 * 并实现其  doGetAuthenticationInfo()和 doGetAuthorizationInfo 两个方法
 */
@SuppressWarnings("serial")
public class ShiroRealm extends AuthorizingRealm {
    public static Map<String, UserEntity> userMap = new HashMap<String, UserEntity>(16);
    public static Map<String, Set<String>> roleMap = new HashMap<String, Set<String>>(16);
    public static Map<String, Set<String>> permMap = new HashMap<String, Set<String>>(16);

    static {
        // 密码为123456
        UserEntity user1 = new UserEntity(1L, "Alen", "f1f9f1d73234cffbb92b80494fbdaac01a525bd7", "艾伦", false);
        // 密码为123456
        UserEntity user2 = new UserEntity(2L, "Chaos", "cce369436bbb9f0325689a3a6d5d6b9b8a3f39a0", "查尔斯", false);

        userMap.put("Alen", user1);
        userMap.put("Chaos", user2);

        roleMap.put("Alen", new HashSet<String>() {
            {
                add("admin");
            }
        });
        roleMap.put("Chaos", new HashSet<String>() {
            {
                add("guest");
            }
        });

        permMap.put("Chaos", new HashSet<String>() {
            {
                add("article:read");
            }
        });
    }

    /**
     * 限定这个 Realm 只处理 UsernamePasswordToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 查询数据库，将获取到的用户安全数据封装返回
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 从 AuthenticationToken 中获取当前用户
        String username = (String) token.getPrincipal();
        // 查询数据库获取用户信息，此处使用 Map 来模拟数据库
        UserEntity user = userMap.get(username);

        // 用户不存在
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        // 用户被锁定
        if (user.getLocked()) {
            throw new LockedAccountException("该用户已被锁定,暂时无法登录！");
        }

        // 使用用户名作为盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        /**
         * 将获取到的用户数据封装成 AuthenticationInfo 对象返回，此处封装为 SimpleAuthenticationInfo 对象。
         *  参数1. 认证的实体信息，可以是从数据库中获取到的用户实体类对象或者用户名
         *  参数2. 查询获取到的登录密码
         *  参数3. 盐值
         *  参数4. 当前 Realm 对象的名称，直接调用父类的 getName() 方法即可
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
        return info;
    }

    //授权shiro会回调的方法

    /**
     * 查询数据库，将获取到的用户的角色及权限信息返回
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户
        UserEntity currentUser = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        // UserEntity currentUser = (UserEntity)principals.getPrimaryPrincipal();
        // 查询数据库，获取用户的角色信息
        Set<String> roles = roleMap.get(currentUser.getName());
        // 查询数据库，获取用户的权限信息
        Set<String> perms = permMap.get(currentUser.getName());

        // 创建 SimpleAuthorizationInfo, 并设置其 角色、权限 属性
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(perms);
        // 返回 SimpleAuthorizationInfo 对象
        return info;
    }
}

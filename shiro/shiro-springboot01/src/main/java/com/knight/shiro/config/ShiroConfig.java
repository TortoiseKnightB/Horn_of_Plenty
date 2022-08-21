package com.knight.shiro.config;

import com.knight.shiro.realm.AccountRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author TortoiseKnightB
 * @date 2022/08/20
 */
@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //权限设置
        Map<String, String> map = new Hashtable<>();
        map.put("/main", "authc");
        map.put("/manage", "perms[manage]");
        map.put("/administrator", "roles[administrator]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth.html");
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(AccountRealm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 设置加密算法和迭代次数
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        matcher.setHashIterations(3);
        realm.setCredentialsMatcher(matcher);

        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }


    @Bean
    public AccountRealm accountRealm() {
        return new AccountRealm();
    }
}

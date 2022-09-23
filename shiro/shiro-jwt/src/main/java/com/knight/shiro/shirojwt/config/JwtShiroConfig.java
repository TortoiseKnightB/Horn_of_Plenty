package com.knight.shiro.shirojwt.config;

import com.knight.shiro.shirojwt.filter.JwtFilter;
import com.knight.shiro.shirojwt.realm.JwtShiroRealm;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;

import javax.servlet.Filter;
import java.util.HashMap;

/**
 * @author TortoiseKnightB
 * @date 2022/09/22
 */

@Configuration
public class JwtShiroConfig {

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();

        //session管理
//        webSecurityManager.setSessionManager(sessionManager());

        //realm管理
        webSecurityManager.setRealm(realm());

        //缓存管理
        webSecurityManager.setCacheManager(new MemoryConstrainedCacheManager());
        //使用ehcache
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        ehCacheManager.setCacheManager(getEhCacheManager());
//        webSecurityManager.setCacheManager(ehCacheManager);

        //redis实现
//        webSecurityManager.setCacheManager(redisCacheManager());

        //关闭session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        webSecurityManager.setSubjectDAO(subjectDAO);

        return webSecurityManager;
    }

//    @Bean
//    public RedisCacheManager redisCacheManager() {
//
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost("localhost:6379");
//        redisManager.setDatabase(1);
//        redisManager.setTimeout(5000);
////        redisManager.setPassword();
//
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager);
//        return redisCacheManager;
//    }
//
//    @Bean
//    public CacheManager getEhCacheManager() {
//        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
//        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("classpath:org/apache/shiro/cache/ehcache/ehcache.xml"));
//        return ehCacheManagerFactoryBean.getObject();
//    }

    @Bean
    public Realm realm() {
        JwtShiroRealm shiroRealm = new JwtShiroRealm();
        return shiroRealm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth.html");
//        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setFilterChainDefinitions(
                "/accountLogin = anon\n" +
                        "/login.html = anon\n" +
                        "/logout = logout\n" +
                        "/user = jwt,authc,perms[user:list]\n" +
                        "/** = jwt\n" +
                        "");

        // 加入自定义的Filter
        HashMap<String, Filter> myFIleter = new HashMap<>();
        myFIleter.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(myFIleter);

        return shiroFilterFactoryBean;
    }

}



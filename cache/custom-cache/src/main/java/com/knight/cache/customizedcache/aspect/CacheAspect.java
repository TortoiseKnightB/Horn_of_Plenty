package com.knight.cache.customizedcache.aspect;

import com.knight.cache.customizedcache.config.MyCache;
import com.knight.cache.customizedcache.config.MyCacheManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TortoiseKnightB
 * @date 2022/09/14
 */
@Aspect
@Component
public class CacheAspect implements Ordered {
    @Autowired
    private CacheManager cacheManager;

    @Before("@annotation(org.springframework.cache.annotation.Cacheable)")
    public void beforeCacheable(JoinPoint pjp) throws Throwable {
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        setCacheNames(ms, CacheConfig.class);
        setCacheNames(ms, Cacheable.class);
    }

    @Before("@annotation(org.springframework.cache.annotation.CachePut)")
    public void beforeCachePut(JoinPoint pjp) throws Throwable {
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        setCacheNames(ms, CacheConfig.class);
        setCacheNames(ms, CachePut.class);
    }

    @Before("@annotation(org.springframework.cache.annotation.CacheEvict)")
    public void beforeCacheEvict(JoinPoint pjp) throws Throwable {
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        setCacheNames(ms, CacheConfig.class);
        setCacheNames(ms, CacheEvict.class);
    }

    private void setCacheNames(MethodSignature ms, Class cls) {
        Annotation[] annotationsClass = ms.getDeclaringType().getAnnotationsByType(cls);
        Annotation[] annotationsMethod = ms.getMethod().getAnnotationsByType(cls);
        Set<Annotation> set = new HashSet<>();
        set.addAll(Arrays.asList(annotationsClass));
        set.addAll(Arrays.asList(annotationsMethod));
        // 获取原来已经存在的Cache
        Set<MyCache> setCache = new HashSet<>();
        Collection<? extends MyCache> caches = ((MyCacheManager) cacheManager).getCaches();
        setCache.addAll(caches);
        for (Annotation item : set) {
            if (item instanceof CacheConfig) {
                CacheConfig config = (CacheConfig) item;
                for (String s : config.cacheNames()) {
                    setCache.add(new MyCache(s));
                }
            } else if (item instanceof Cacheable) {
                Cacheable config = (Cacheable) item;
                String[] strings = config.cacheNames();
                String[] values = config.value();
                Set<String> nameSet = new HashSet<>();
                nameSet.addAll(Arrays.asList(strings));
                nameSet.addAll(Arrays.asList(values));
                for (String s : nameSet) {
                    setCache.add(new MyCache(s));
                }
            }
        }
        ((MyCacheManager) cacheManager).setCaches(setCache);
        ((MyCacheManager) cacheManager).initializeCaches();
    }

    // 优先执行
    @Override
    public int getOrder() {
        return -999;
    }

}

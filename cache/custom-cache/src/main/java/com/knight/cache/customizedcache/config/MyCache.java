package com.knight.cache.customizedcache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TortoiseKnightB
 * @date 2022/09/13
 */
public class MyCache implements Cache {
    final static Logger logger = LoggerFactory.getLogger(MyCache.class);

    String name;
    Map<Object, Object> store = new ConcurrentHashMap<Object, Object>();

    public MyCache() {
    }

    public MyCache(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    @Override
    public Object getNativeCache() {
        return store;
    }


    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper result = null;
        Object thevalue = store.get(key);
        if(thevalue!=null) {
            logger.info("["+name+"]got cache, key:"+key);
            result = new SimpleValueWrapper(thevalue);
        }else{
            logger.info("["+name+"]missing cache, key:"+key);
        }
        return result;
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> type) {
        ValueWrapper vw = get(key);
        if(vw==null){
            return null;
        }
        return (T)vw.get();
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        ValueWrapper vw = get(key);
        if(vw==null){
            return null;
        }
        return (T)vw.get();
    }


    @Override
    public void put(Object key, Object value) {
        store.put(key, value);
    }


    @Override
    public Cache.ValueWrapper putIfAbsent(Object key, Object value) {
        Object existing = this.store.putIfAbsent(key, value);
        return (existing != null ? new SimpleValueWrapper(existing) : null);
    }


    @Override
    public void evict(Object key) {
        store.remove(key);
    }


    @Override
    public void clear() {
        store.clear();
    }
}

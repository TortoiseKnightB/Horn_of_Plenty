//package com.knight.multi_level_cache.manager;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Component;
//
///**
// * @author tortoiseKnight
// * @date 2021/09/28
// */
//@Component
//public class SpringFactory implements ApplicationContextAware {
//
//    private static ApplicationContext applicationContext;
//
//    private SpringFactory() {
//    }
//
//    @Override
//    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
//        SpringFactory.applicationContext = applicationContext;
//    }
//
//    private static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
//
//    public static Object getBean(String name) {
//        return getApplicationContext().getBean(name);
//    }
//
//    public static <T> T getBean(Class<T> clazz) {
//        return getApplicationContext().getBean(clazz);
//    }
//
//    /**
//     * 获取spring.profiles.active
//     */
//    public static String getActiveProfile() {
//        return getApplicationContext().getEnvironment().getActiveProfiles()[0];
//    }
//
//}
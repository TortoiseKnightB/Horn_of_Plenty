package com.knight.cache;

import com.github.benmanes.caffeine.cache.*;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

// https://segmentfault.com/a/1190000038665523

/**
 * @author TortoiseKnightB
 * @date 2022/08/27
 */
class CaffeineApplicationTest {

    @DisplayName("手动加载")
    @Test
    public void test01() {
        // 初始化缓存，设置了1分钟的写过期，100的缓存最大个数
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
        int key1 = 1;
        // 使用getIfPresent方法从缓存中获取值。如果缓存中不存在指定的值，则方法将返回 null：
        System.out.println(cache.getIfPresent(key1));

        // 也可以使用 get 方法获取值，该方法将一个参数为 key 的 Function 作为参数传入。如果缓存中不存在该 key
        // 则该函数将用于提供默认值，该值在计算后插入缓存中：
        System.out.println(cache.get(key1, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return 2;
            }
        }));

        // 校验key1对应的value是否插入缓存中
        System.out.println(cache.getIfPresent(key1));

        // 手动put数据填充缓存中
        int value1 = 1;
        cache.put(key1, value1);

        // 使用getIfPresent方法从缓存中获取值。如果缓存中不存指定的值，则方法将返回 null：
        System.out.println(cache.getIfPresent(1));

        // 移除数据，让数据失效
        cache.invalidate(1);
        System.out.println(cache.getIfPresent(1));
    }

    @DisplayName("同步加载数据")
    @Test
    public void test02() {
        // 初始化缓存，设置了1分钟的写过期，100的缓存最大个数
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(new CacheLoader<Integer, Integer>() {
                    @Nullable
                    @Override
                    public Integer load(@NonNull Integer key) {
                        return getInDB(key);
                    }
                });

        int key1 = 1;
        // get数据，取不到则从数据库中读取相关数据，该值也会插入缓存中：
        Integer value1 = cache.get(key1);
        System.out.println(value1);

        // 支持直接get一组值，支持批量查找
        Map<Integer, Integer> dataMap
                = cache.getAll(Arrays.asList(1, 2, 3));
        System.out.println(dataMap);
    }

    @DisplayName("异步加载数据")
    @Test
    public void test03() throws ExecutionException, InterruptedException {
        // 使用executor设置线程池
        AsyncCache<Integer, Integer> asyncCache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .executor(Executors.newSingleThreadExecutor())
                .buildAsync();

        Integer key = 1;
        // get返回的是CompletableFuture
        CompletableFuture<Integer> future = asyncCache.get(key, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer key) {
                // 执行所在的线程不在是main，而是ForkJoinPool线程池提供的线程
                System.out.println("当前所在线程：" + Thread.currentThread().getName());
                int value = getInDB(key);
                return value;
            }
        });

        int value = future.get();
        System.out.println("当前所在线程：" + Thread.currentThread().getName());
        System.out.println(value);
    }


    @DisplayName("基于大小淘汰")
    @Test
    public void test04() throws InterruptedException {
        // 初始化缓存，缓存最大个数为1
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .maximumSize(1)
                .build();

        cache.put(1, 1);
        // 打印缓存个数，结果为1
        System.out.println(cache.estimatedSize());

        cache.put(2, 2);
        // 稍微休眠一秒(淘汰数据是一个异步的过程，休眠一秒等异步的回收结束)
        Thread.sleep(1000);
        // 打印缓存个数，结果为1
        System.out.println(cache.estimatedSize());
    }

    @DisplayName("基于权重淘汰")
    @Test
    public void test05() throws InterruptedException {
        // 初始化缓存，设置最大权重为2
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .maximumWeight(2)
                .weigher(new Weigher<Integer, Integer>() {
                    @Override
                    public @NonNegative int weigh(@NonNull Integer key, @NonNull Integer value) {
                        return key;
                    }
                })
                .build();

        cache.put(1, 1);
        // 打印缓存个数，结果为1
        System.out.println(cache.estimatedSize());

        cache.put(2, 2);
        // 稍微休眠一秒
        Thread.sleep(1000);
        // 打印缓存个数，结果为1
        System.out.println(cache.estimatedSize());
    }


    /**
     * 访问后到期，时间节点从最近一次读或者写，也就是get或者put开始算起
     *
     * @throws InterruptedException
     */
    @DisplayName("基于时间淘汰_访问后到期")
    @Test
    public void test06() throws InterruptedException {
        // 设置访问5秒后数据到期
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .scheduler(Scheduler.systemScheduler())
                .build();

        cache.put(1, 2);
        System.out.println(cache.getIfPresent(1));

        Thread.sleep(6000);

        System.out.println(cache.getIfPresent(1));
    }


    /**
     * 写入后到期，时间节点从写开始算起，也就是put
     *
     * @throws InterruptedException
     */
    @DisplayName("基于时间淘汰_写入后到期")
    @Test
    public void test07() throws InterruptedException {
        // 设置写入5秒后数据到期
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .scheduler(Scheduler.systemScheduler())
                .build();
        cache.put(1, 2);
        System.out.println(cache.getIfPresent(1));

        Thread.sleep(6000);

        System.out.println(cache.getIfPresent(1));
    }

    @DisplayName("基于时间淘汰_自定义过期时间")
    @Test
    public void test08() throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfter(new Expiry<Integer, Integer>() {
                    // 创建1秒后过期，可以看到这里必须要用纳秒
                    @Override
                    public long expireAfterCreate(@NonNull Integer key, @NonNull Integer value, long currentTime) {
                        return TimeUnit.SECONDS.toNanos(1);
                    }

                    // 更新2秒后过期，可以看到这里必须要用纳秒
                    @Override
                    public long expireAfterUpdate(@NonNull Integer key, @NonNull Integer value, long currentTime, @NonNegative long currentDuration) {
                        return TimeUnit.SECONDS.toNanos(2);
                    }

                    // 读3秒后过期，可以看到这里必须要用纳秒
                    @Override
                    public long expireAfterRead(@NonNull Integer key, @NonNull Integer value, long currentTime, @NonNegative long currentDuration) {
                        return TimeUnit.SECONDS.toNanos(3);
                    }
                })
                .scheduler(Scheduler.systemScheduler())
                .build();

        cache.put(1, 2);

        System.out.println(cache.getIfPresent(1));

        Thread.sleep(6000);

        System.out.println(cache.getIfPresent(1));
    }

    @DisplayName("基于引用淘汰_弱引用")
    @Test
    public void test09() {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                // 设置Key为弱引用，生命周期是下次gc的时候
                .weakKeys()
                // 设置value为弱引用，生命周期是下次gc的时候
                .weakValues()
                .build();

        cache.put(1, 2);
        System.out.println(cache.getIfPresent(1));
        // 强行调用一次GC
        // System.gc() 不一定会真的触发GC，只是一种通知机制，但是并非一定会发生GC，垃圾收集器进不进行GC是不确定的，所以有概率看到设置weakKeys了却在调用System.gc() 的时候却没有丢失缓存数据的情况
        System.gc();

        System.out.println(cache.getIfPresent(1));
    }

    @DisplayName("基于引用淘汰_软引用")
    @Test
    public void test10() {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                // 设置value为软引用，生命周期是GC时并且堆内存不够时触发清除
                .softValues()
                .build();

        cache.put(1, 2);
        System.out.println(cache.getIfPresent(1));
        // 强行调用一次GC
        System.gc();

        System.out.println(cache.getIfPresent(1));
    }

    @DisplayName("刷新机制")
    @Test
    public void test11() {

    }


    @Test
    public void test001() {
        Cache<String, Integer> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .recordStats()
//                .expireAfterWrite(5, TimeUnit.SECONDS)
//                .expireAfterAccess(2, TimeUnit.SECONDS)
                .expireAfter(new Expiry<String, Integer>() {
                    @Override
                    public long expireAfterCreate(@NonNull String key, @NonNull Integer value, long currentTime) {
                        return currentTime;
                    }

                    @Override
                    public long expireAfterUpdate(@NonNull String key, @NonNull Integer value, long currentTime, @NonNegative long currentDuration) {
                        return currentDuration;
                    }

                    @Override
                    public long expireAfterRead(@NonNull String key, @NonNull Integer value, long currentTime, @NonNegative long currentDuration) {
                        return currentDuration;
                    }
                })
                .removalListener(new RemovalListener<String, Integer>() {
                    @Override
                    public void onRemoval(@Nullable String key, @Nullable Integer value, @NonNull RemovalCause cause) {
                        System.out.println("移除了key：" + key + " value :" + value + " cause : " + cause);
                    }
                })
                .build();


        cache.put("cliffcw1", 1);
//        cache.put("cliffcw2", 2);
//        cache.put("cliffcw3", 3);

        cache.policy().expireVariably().ifPresent(policy -> {
            policy.put("cliffcw1", 1, 3, TimeUnit.SECONDS);
            policy.put("cliffcw2", 2, 6, TimeUnit.SECONDS);
        });

        System.out.println(cache.getIfPresent("cliffcw1"));
        System.out.println(cache.getIfPresent("cliffcw2"));

//        CacheStats stats = cache.stats();
//        long hitCount = stats.hitCount();
//        System.out.println("命中率1：" + hitCount);

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(cache.getIfPresent("cliffcw1"));
//        System.out.println(cache.getIfPresent("cliffcw2"));

//        long hitCount2 = stats.hitCount();
//        System.out.println("命中率2：" + hitCount2);

//        double hitRate = stats.hitRate();
//        System.out.println(hitRate);
    }

    @Test
    public void test002() {
        Cache<String, Integer> cache = Caffeine.newBuilder()
//                .maximumSize(2)
                // 自定义weight上限
                .maximumWeight(20)
                .weigher(new Weigher<Object, Object>() {
                    // 根据value返回weight值
                    @Override
                    public @NonNegative int weigh(@NonNull Object key, @NonNull Object value) {
                        return 10;
                    }
                })
                .removalListener(new RemovalListener<String, Integer>() {
                    @Override
                    public void onRemoval(@Nullable String key, @Nullable Integer value, @NonNull RemovalCause cause) {
                        System.out.println("移除了key：" + key + " value：" + value + " cause：" + cause);
                    }
                })
                .build();

        cache.put("key1", 1);
        cache.put("key2", 2);
        cache.put("key3", 3);

        System.out.println(cache.getIfPresent("key1"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * 模拟从数据库中读取key
     *
     * @param key
     * @return
     */
    private int getInDB(int key) {
        return key + 1;
    }

    private int index = 1;

    /**
     * 模拟从数据库中读取数据
     *
     * @return
     */
    private int getInDB() {
        // 这里为了体现数据重新被get，因而用了index++
        index++;
        return index;
    }

}
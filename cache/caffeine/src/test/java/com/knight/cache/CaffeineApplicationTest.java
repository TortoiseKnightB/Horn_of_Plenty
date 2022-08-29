package com.knight.cache;

import com.github.benmanes.caffeine.cache.*;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * 参考文章：<a href="https://segmentfault.com/a/1190000038665523">全网最权威的Caffeine教程</a>
 *
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
    public void test11() throws InterruptedException {
        // 设置写入后3秒后数据过期，2秒后如果有数据访问则刷新数据
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .expireAfterWrite(35, TimeUnit.SECONDS)
                .build(new CacheLoader<Integer, Integer>() {
                    @Nullable
                    @Override
                    public Integer load(@NonNull Integer key) {
                        System.out.println("load");
                        return getInDB();
                    }

                    @Override
                    public @Nullable Integer reload(@NonNull Integer key, @NonNull Integer oldValue) throws Exception {
                        System.out.println("reload");
                        return getInDB();
                    }
                });

        cache.put(1, getInDB());
        System.out.println("start index = " + index);

        // 休眠2.5秒，后取值。因为超过2秒，触发刷新，但本次的取值仍为旧值
        Thread.sleep(2500);
        System.out.println(cache.getIfPresent(1));
        // 等待刷新完成
        Thread.sleep(500);
        // 取刷新后的新值。本次间隔不超过2秒，不触发刷新
        System.out.println(cache.getIfPresent(1));

        Thread.sleep(2500);
        // 超过2秒，触发刷新，取旧值
        System.out.println(cache.getIfPresent(1));
        Thread.sleep(500);
        // 取刷新后的新值。本次间隔不超过2秒，不触发刷新
        System.out.println(cache.getIfPresent(1));

        // 间隔不超过2秒，不触发刷新
        Thread.sleep(1000);
        System.out.println(cache.getIfPresent(1));
        // 间隔不超过2秒，不触发刷新
        Thread.sleep(500);
        System.out.println(cache.getIfPresent(1));
    }

    @DisplayName("模拟二级缓存")
    @Test
    public void test12() throws InterruptedException {
        // 设置最大缓存个数为1
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .maximumSize(1)
                // 设置put和remove的回调
                .writer(new CacheWriter<Integer, Integer>() {
                    @Override
                    public void write(@NonNull Integer key, @NonNull Integer value) {
                        secondCacheMap.put(key, new WeakReference<>(value));
                        System.out.println("触发CacheWriter.write，将key = " + key + "放入二级缓存中");
                    }

                    @Override
                    public void delete(@NonNull Integer key, @Nullable Integer value, @NonNull RemovalCause cause) {
                        switch (cause) {
                            case EXPLICIT:
                                secondCacheMap.remove(key);
                                System.out.println("触发CacheWriter" +
                                        ".delete，清除原因：主动清除，将key = " + key +
                                        "从二级缓存清除");
                                break;
                            case SIZE:
                                System.out.println("触发CacheWriter" +
                                        ".delete，清除原因：缓存个数超过上限，key = " + key);
                                break;
                            default:
                                break;
                        }
                    }
                })
                .build(new CacheLoader<Integer, Integer>() {
                    @Nullable
                    @Override
                    public Integer load(@NonNull Integer key) {
                        WeakReference<Integer> value = secondCacheMap.get(key);
                        if (value == null) {
                            return null;
                        }

                        System.out.println("触发CacheLoader.load，从二级缓存读取key = " + key);
                        return value.get();
                    }
                });

        cache.put(1, 1);
        cache.put(2, 2);
        // 由于清除缓存是异步的，因而睡眠1秒等待清除完成
        Thread.sleep(1000);

        // 缓存超上限触发清除后
        // Caffeine在调用CacheLoader.load拿到非null的数据后会重新放入缓存中，这样便导致缓存个数又超过了最大的上限了，所以清除了key为2的缓存
        System.out.println("从Caffeine中get数据，key为1，value为" + cache.get(1));
    }

    @DisplayName("统计数据")
    @Test
    public void test13() {
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                // 开启记录
                .recordStats()
                .build(new CacheLoader<Integer, Integer>() {
                    @Override
                    public @Nullable Integer load(@NonNull Integer key) {
                        return getInDB(key);
                    }
                });

//        cache.put(1,2);
        cache.get(1);

        // 命中率
        System.out.println(cache.stats().hitRate());
        // 被剔除的数量
        System.out.println(cache.stats().evictionCount());
        // 加载新值所花费的平均时间[纳秒]
        System.out.println(cache.stats().averageLoadPenalty());
    }

    /**
     * EXPLICIT：如果原因是这个，那么意味着数据被我们手动的remove掉了
     * <p>
     * REPLACED：就是替换了，也就是put数据的时候旧的数据被覆盖导致的移除
     * <p>
     * COLLECTED：这个有歧义点，其实就是收集，也就是垃圾回收导致的，一般是用弱引用或者软引用会导致这个情况
     * <p>
     * EXPIRED：数据过期，无需解释的原因。
     * <p>
     * SIZE：个数超过限制导致的移除
     *
     * @throws InterruptedException
     */
    @DisplayName("淘汰监听")
    @Test
    public void test14() throws InterruptedException {
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .scheduler(Scheduler.systemScheduler())
                // 增加了淘汰监听
                .removalListener(((key, value, cause) -> {
                    System.out.println("淘汰通知，key：" + key + "，原因：" + cause);
                }))
                .build(new CacheLoader<Integer, Integer>() {
                    @Override
                    public @Nullable Integer load(@NonNull Integer key) throws Exception {
                        return key;
                    }
                });

        cache.put(1, 2);

        Thread.currentThread().sleep(3000);

        System.out.println(cache.get(1));
    }

    @DisplayName("为Key设置不同的过期时间")
    @Test
    public void test001() throws InterruptedException {
        Cache<String, Integer> cache = Caffeine.newBuilder()
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

        cache.policy().expireVariably().ifPresent(policy -> {
            policy.put("cliffcw1", 1, 3, TimeUnit.SECONDS);
            policy.put("cliffcw2", 2, 6, TimeUnit.SECONDS);
        });

        System.out.println(cache.getIfPresent("cliffcw1"));
        System.out.println(cache.getIfPresent("cliffcw2"));


        Thread.sleep(5000);

        System.out.println(cache.getIfPresent("cliffcw1"));
        System.out.println(cache.getIfPresent("cliffcw2"));

        Thread.sleep(1500);

        System.out.println(cache.getIfPresent("cliffcw1"));
        System.out.println(cache.getIfPresent("cliffcw2"));
    }

    @Test
    public void test002() {
        Cache<String, Object> cache = Caffeine.newBuilder()
//                .maximumSize(2)
                // 自定义weight上限
                .maximumWeight(20)
                .weigher(new Weigher<Object, Object>() {
                    // 根据value返回weight值
                    @Override
                    public @NonNegative int weigh(@NonNull Object key, @NonNull Object value) {
                        long objectSize = ObjectSizeCalculator.getObjectSize(value);
                        System.out.println(key + "-" + value + " 占用内存：" + objectSize);
                        return (int) objectSize;
                    }
                })
                .removalListener(new RemovalListener<String, Object>() {
                    @Override
                    public void onRemoval(@Nullable String key, @Nullable Object value, @NonNull RemovalCause cause) {
                        System.out.println("移除了key：" + key + " value：" + value + " cause：" + cause);
                    }
                })
                .build();

        cache.put("key1", "1");
        cache.put("key2", 2);
        cache.put("key3", "{name=Alen,age=16}");
        cache.put("key4", "{na");

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

    private int index = 0;

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

    /**
     * 充当二级缓存用，生命周期仅活到下个gc
     */
    private Map<Integer, WeakReference<Integer>> secondCacheMap = new ConcurrentHashMap<>();


}
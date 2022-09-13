# Cache

- [https://segmentfault.com/a/1190000038665523](https://segmentfault.com/a/1190000038665523)

### Redis为什么要设置序列化

- 任何存储都需要序列化。只不过常规你在用DB一类存储的时候，这个事情DB帮你在内部搞定了,而Redis并不会帮你做这个事情
- [redis为什么要序列化](https://www.php.cn/redis/436244.html)
- [存入redis中的数据为什么要序列化](https://blog.csdn.net/weixin_43968372/article/details/106442009)
- JdkSerializationRedisSerializer:这个序列化方法是jdk提供的。首先要求我们要被序列化的类继承自
  Serializable接口,然后通过jdk对象序列化的方法保存。(注:
  这个序列化保存的对象,即使是个 String类型的,在redis控制台,也是看不出来的,因为它保存了一些对象的类型额外信息)
- StringRedisSerializer:就是通过string.getBytes0来实现的。而且在 Redist中,所有存储的值都是字符串类型的。所以这种方法保存后,通过
  Redis-c控制台,可以清楚查看我们保存了什么
- JacksonJsonRedisSerializer: jackson- json工具提供了
  javabean与json之间的转换能力,可以将pojo实例序列化成json格式存储在redis中,也可以将json格式的数据转换成pojo实例。因为
  jackson工具在序列化和反序列化时,需要明确指定Class类型,因此策略封装起来稍微复杂
- [RedisTemplate自定义序列化](https://codeantenna.com/a/6PeI09BhTj)
- [ObjectMapper.activateDefaultTyping详解](https://blog.csdn.net/zzhongcy/article/details/105813105)
- [RedisConfig自定义配置 redis工具类](https://blog.csdn.net/qq_55362295/article/details/121531088)

### Spring Cache

- [官方文档](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache)
- [Spring Cache，从入门到真香](https://zhuanlan.zhihu.com/p/266804094)
- [Spring Cache](https://www.jianshu.com/p/33c019de9115)
- [史上最全的Spring Boot Cache使用与整合](https://blog.csdn.net/qq_32448349/article/details/101696892)

除了CacheConfig只能用于类上，其余的都可以用在类或者方法上，用在方法上好理解，缓存方法结果，如果用在类上，就相当于对该类的所有可以缓存的方法（需要是public方法）加上注解。

@Cacheable

@Cacheble注解表示这个方法的结果可以被缓存，调用该方法前，会先检查对应的缓存key在缓存中是否已经有值，如果有就直接返回，不调用方法，如果没有，就会调用方法，同时把结果缓存起来。

@CacheConfig

有些配置可能又是一个类通用的，这种情况就可以使用@CacheConfig了，它是一个类级别的注解，可以在类级别上配置cacheNames、keyGenerator、cacheManager、cacheResolver等。

@CachePut

@CachePut注解修饰的方法，会把方法的返回值put到缓存里面缓存起来，它只是触发put的动作，和@Cacheable不同，不会读取缓存，put到缓存的值进程内其他场景的使用者就可以使用了。

@CacheEvict

@CacheEvict注解修饰的方法，会触发缓存的evict操作，清空缓存中指定key的值。

@Caching

@Caching能够支持多个缓存注解生效。

- [玩转Spring Cache --- 整合分布式缓存Redis Cache（使用Lettuce、使用Spring Data Redis）](https://cloud.tencent.com/developer/article/1497594)

https://www.cnblogs.com/shog808/p/13826206.html
https://cloud.tencent.com/developer/article/1497599
https://www.cnblogs.com/lullaby/p/9826665.html
https://blog.csdn.net/software_Manito/article/details/110136562

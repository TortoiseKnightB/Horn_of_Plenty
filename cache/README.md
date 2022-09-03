# Cache

### Redis为什么要设置序列化

- 任何存储都需要序列化。只不过常规你在用DB一类存储的时候，这个事情DB帮你在内部搞定了,而Redis并不会帮你做这个事情
- [redis为什么要序列化](https://www.php.cn/redis/436244.html)
- [存入redis中的数据为什么要序列化](https://blog.csdn.net/weixin_43968372/article/details/106442009)
- JdkSerializationRedisSerializer:这个序列化方法是jdk提供的。首先要求我们要被序列化的类继承自 Serializable接口,然后通过jdk对象序列化的方法保存。(注:
  这个序列化保存的对象,即使是个 String类型的,在redis控制台,也是看不出来的,因为它保存了一些对象的类型额外信息)
- StringRedisSerializer:就是通过string.getBytes0来实现的。而且在 Redist中,所有存储的值都是字符串类型的。所以这种方法保存后,通过 Redis-c控制台,可以清楚查看我们保存了什么
- JacksonJsonRedisSerializer: jackson- json工具提供了
  javabean与json之间的转换能力,可以将pojo实例序列化成json格式存储在redis中,也可以将json格式的数据转换成pojo实例。因为
  jackson工具在序列化和反序列化时,需要明确指定Class类型,因此策略封装起来稍微复杂
- [RedisTemplate自定义序列化](https://codeantenna.com/a/6PeI09BhTj)
- [ObjectMapper.activateDefaultTyping详解](https://blog.csdn.net/zzhongcy/article/details/105813105)
# Multi_Level_Cache

- 多级缓存
- 在 Spring Boot 2.0 中，Lettuce 取代了 Redis，需要引入依赖 commons-pool2

```text
官方文档如下：
Lettuce is now used instead of Jedis as the Redis driver when you use spring-boot-starter-data-redis. If you are using higher level Spring Data constructs you should find that the change is transparent.

We still support Jedis. Switch dependencies if you prefer Jedis by excluding io.lettuce:lettuce-core and adding redis.clients:jedis instead.

Connection pooling is optional and, if you are using it, you now need to add commons-pool2 yourself as Lettuce, contrary to Jedis, does not bring it transitively.
```

```text
在 pom.xml 中引入依赖：
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
```


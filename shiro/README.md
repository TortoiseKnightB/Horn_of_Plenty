# Shiro

- [官方文档](https://shiro.apache.org/documentation.html)
- [【硬核干货】2小时学会Spring Boot整合Shiro-哔哩哔哩](https://b23.tv/pY4xRcY)

### 基本概念

- 用户、角色、权限
- 给角色赋予权限，给用户赋予角色

### 核心组件

- [UsernamePasswordToken](https://shiro.apache.org/static/1.9.1/apidocs/org/apache/shiro/authc/UsernamePasswordToken.html)
  封装用户登录信息，创建token
- SecurityManager 负责安全认证和授权
- Subject 包含了用户信息
- Realm 开发者自定义的模块，验证和授权的逻辑全部写在这里
- AuthenticationInfo 用户角色信息集合，认证时使用
- AuthorizationInfo 角色权限信息集合，授权时使用
- DefaultWebSecurityManager 安全管理器，开发者自定义的 Realm 需要注入到其中才能生效
- ShiroFilterFactoryBean 过滤器工厂，Shiro 的具体执行操作就是由 ShiroFilterFactoryBean 创建的一个个 Filter 对象来完成

<p align="center">
        <img src="./img/shiro流程图.jpg" width="600"/>
</p>

### JWT标准声明

- iss: jwt签发者
- sub: jwt所面向的用户 //这个以后就是放我们登录的用户名
- aud: 接收jwt的一方
- exp: jwt的过期时间，这个过期时间必须要大于签发时间 //过期时间也可以放
- nbf: 定义在什么时间之前，该jwt都是不可用的.
- iat: jwt的签发时间
- jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击

### 推荐文章

- [官方文档](https://shiro.apache.org/reference.html)
- [shiro源码分析](https://blog.csdn.net/qq_38366063/article/details/103755431)
- [springboot整合Shiro+jwt 前后端分离 超级详细的shiro+jwt鉴权过程](https://blog.csdn.net/Gorho/article/details/115921028)
- [Shiro 框架 BasicHttpAuthenticationFilter 认证流程](https://blog.csdn.net/saienenen/article/details/111031108)
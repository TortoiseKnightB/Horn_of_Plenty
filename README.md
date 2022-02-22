# my_server

- 我的服务器配置
- 本项目主要记录私人服务器的搭建记录，主要用来进行部署 mysql、redis 等服务，以及考虑今后搭建个人网站
- 目前已部署项目
  - mysql：docker    作为 nacos 持久化数据库，部署在 alicloud
  - nacos：docker    微服务配置中心、注册中心，部署在 txcloud
  - sentinel：docker    流控、降级、熔断、监控等，由于服务器无法访问本地内部网络，暂时搁置（本地部署已经成功）
  - seata：/root/local    分布式事务控制中心，远程部署出现问题，怀疑原因同上 sentinel（本地部署已经成功）
  - jdk：/root/local    jdk1.8，主要用来运行一些服务，部署在 txcloud
  - elasticsearch：docker    日志存储记录查询中心，部署在 txcloud
  - kibana：docker    日志查询结果展示组件，部署在 txcloud
  - logstash    暂未部署在远程服务器，只在本地进行了配置。负责将本地输出日志 传给 elasticsearch

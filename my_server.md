# MyServer

- 本文档用来记录我的服务器配置信息，以便日后查询

### 偏好设置

- 设置主机名 `vim /etc/hostname`，重启生效
- 设置终端提示符信息 `PS1=[\u@\h \W]\$`

### 目录结构

```
/root/    # 用户名为 root
├── local        # 存放本地应用文件
│   └── docker    
│
└── my_tar    # 存放压缩包
```

### JDK

- 从官网（https://www.oracle.com/java/technologies/downloads/#java8）下载 jdk 压缩包，放入 `/root/my_tar` 文件夹下
- 解压到对应位置

```bash
cd /root/local
mkdir java
tar -zxv -f /root/my_tar/jdk-8u321-linux-x64.tar.gz -C /root/local/java/
```

- 解压后我的文件路径为 `/root/local/java/jdk1.8.0_321`
- 设置环境变量

```bash
vim /etc/profile
####### 末尾添加 ######
export JAVA_HOME=/root/local/java/jdk1.8.0_321
export PATH=$JAVA_HOME/bin:$PATH
# 让修改生效
source /etc/profile
# 检查测试
echo $PATH
java -version
```

------

### Docker

- 官方安装文档：https://docs.docker.com/engine/install/centos/
- 卸载旧版本

```bash
 sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```

- 安装需要的软件包

```bash
sudo yum install -y yum-utils
```

- 设置镜像仓库（换成国内阿里云镜像源）

```bash
 sudo yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

- 若出现问题 `Invalid configuration value: failovermethod=priority in /etc/yum.repos.d/CentOS-epel.repo; 配置：ID 为 "failovermethod" 的 OptionBinding 不存在`，大概的意思为配置值  failovermethod 无效，这里将它注释掉即可

```bash
vim /etc/yum.repos.d/CentOS-epel.repo

[epel]
name=Extra Packages for Enterprise Linux 8 - $basearch
baseurl=http://mirrors.cloud.aliyuncs.com/epel/8/Everything/$basearch
# failovermethod=priority
enabled=1
gpgcheck=0
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-EPEL-8
```

- 更新 yum 软件包索引

```bash
yum makecache fast
```

- 安装 Docker CE（社区免费版）

```bash
sudo yum install docker-ce
```

- 启动 docker

```bash
systemctl start docker
```

- 测试

```bash
docker version

docker run hello-world
```

- docker 默认安装路径为 `/var/lib/docker`，在我们自己的文件夹 `/root/local/` 下创建软链接

```bash
ln -s /var/lib/docker /root/local/
```

------

### MySQL

##### 在 docker 上安装

- 从 docker 安装 mysql
- docker hub上面查找 mysql 镜像

```bash
docker search mysql
```

- 从docker hub上拉取tomcat镜像到本地

```bash
docker pull mysql
```

- 启动 mysql 容器

```bash
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql

-p 1234:3306：将主机的 1234 端口映射到 docker 容器的 3306 端口
-e MYSQL_ROOT_PASSWORD=password：初始化 root 用户的密码
-d mysql : 后台程序运行 mysql 
```

- 进入 mysql 容器

```bash
docker exec -it mysql（或MySQL运行成功后的容器ID） /bin/bash 
```

- 登录 mysql

```bash
mysql -u root -p
```

- 打开阿里云服务器防火墙 3306 端口，用客户端（如 MySQL Workbench）可以连接成功

------

##### 文件结构

- 配置文件路径 `/etc/mysql/my.cnf` 等（查找语句 ` mysql --help | grep my.cnf`）

##### 在 mysql 中创建 nacos 数据库

- 将 .sql 文件传入 docker

```bash
# 先将 nacos 文件夹里面的 nacos-mysql.sql 文件传到云服务器
# a910f9d80e8a 为容器id，.sql 暂存在/tmp
docker cp ./nacos-mysql.sql a910f9d80e8a:/tmp
```

- 进入 docker 中的 mysql

```bash
docker exec -it mysql /bin/bash 
mysql -u root -p
```

- 创建 nacos 配置数据库

```sql
CREATE DATABASE nacos_config;
USE nacos_config;

show databases;
```

- 导入 .sql 文件

```bash
source /tmp/nacos-mysql.sql;
show tables;
```

------

------

------

------

##### 已废弃计划

- 官网下载压缩包（Linux 5.7版本）（https://dev.mysql.com/downloads/mysql/）
- 好像需要依赖 libaio 库，阿里云服务器自带，这里就略过

------

// TODO    以下自定义配置失效，暂时略过，以后补充

- ~~解压到 /usr/local/，并改名为 mysql~~
- ~~初始化数据库~~ 

```bash
cd /root/local/mysql/
./bin/mysqld --user=root --basedir=/root/local/mysql --datadir=/root/local/mysql/data --initialize
```

- ~~由于我们自定义了路径，需要修改 MySQL 配置文件~~

```bash
......
# Set some defaults
mysqld_pid_file_path=
if test -z "$basedir"
then
  basedir=/root/local/mysql
  bindir=/root/local/mysql/bin
  if test -z "$datadir"
  then
    datadir=/root/local/mysql/data
  fi
  sbindir=/root/local/mysql/bin
  libexecdir=/root/local/mysql/bin
else
......
```

// END

------

- mysql 默认指定安装目录为 `/usr/local/mysql`，所以将解压好的 mysql 文件放到 `/usr/local/`，并改名为 mysql
- 初始化数据库

```bash
cd /usr/local/mysql/ ; \
./bin/mysqld --user=root --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data --initialize
```

- 从提示信息中找到初始密码

- 启动服务

- 发现 mysql 中的 bin 目录下没有 mysql.server 文件（mysql.server 实际上为一个链接文件，链接 ../support-files/mysql.server，是一个启动脚本）

- 在 bin 目录下创建 mysql.server 的链接  

```bash
cd /usr/local/mysql/
ln -s ./support-files/mysql.server ./bin/mysql.server
```

------

------

------

### Nacos

##### 安装-单例模式

```bash
docker pull nacos/nacos-server 
# 限制占用内存大小，以单例模式启动
docker run --name nacos_1 -e JVM_XMS=256m -e JVM_XMX=256m --env MODE=standalone -p 8848:8848 nacos/nacos-server
# 退出去
docker start nacos_1
docker exec -it nacos_1 /bin/bash
```

- 修改 application.properties 文件，配置外部数据库

```bash
cd /home/nacos/conf
# 先备份，再修改
cp application.properties application.properties.bk
vim application.properties

############ custome ############
spring.datasource.platform=mysql

db.num=1
db.url.0=jdbc:mysql://host:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=GMT%2b8
db.user=root
db.password=password
```

- 改完配置后重新启动 nacos 容器

```bash
# 进入到 nacos/bin，这里自动启动单例模式
sh docker-startup.sh 
# 这里有点小问题，但是不影响，退出docker重启nacos也行
```

- 验证：访问 `http://host:8848/nacos`，登录账号密码都为 nacos

- 注意：
  
  - 在本地配置的时候，为了让 nacos 支持 mysql8.0，需要添加文件夹及数据库驱动 `nacos\plugins\mysql\mysql-connector-java-8.x.xx.jar`

------

### Sentinel

- 存在问题

```bash
目前 sentinel 服务端部署在 linux，sentinel 客户端部署在本机。需要客户端与服务端双向通信（sentinel 服务端能连通本地客户端 ip：端口号，本地能连通 sentinel 服务端 ip：端口号/默认8719） sentinel 才能正常监控，由于服务端无法连通本地客户端，故目前 sentinel 不可用
解决方案：1、服务端与客户端部署在同一台服务器上。2、客户端与服务端互相连通
```

- 可用方案

```bash
无法监控，但是可以通过远程 nacos 直接写入持久化规则，达到流控的目的;

或者直接写入代码（可以在配置中心设置开关，让每个控制参数从配置中心取值，这样在配置中心更改即可）
```

- 规则语法

```bash
#  Nacos 中 Sentinel 的持久化配置规则（在 sentinel 中更改后，规则无法保存最新值。。。）

  resource：资源名称；
  limitApp：来源应用；
  grade：阈值类型，0表示线程数，1表示QPS；
  count：单机阈值；
  strategy：流控模式，0表示直接，1表示关联，2表示链路；
  controlBehavior：流控效果，0表示快速失败，1表示Warm Up，2表示排队等待；
  clusterMode：是否集群
```

##### 在 docker 上安装(没有官方镜像，感觉这个不是太好用，以后自己制作一个)

- 安装镜像

```bash
docker search sentinel
docker pull bladex/sentinel-dashboard
docker images
```

- 启动

```bash
# 从下文发现，sentinel 默认端口为 8858
docker run --name sentinel_1.8.0 -p 8858:8858 bladex/sentinel-dashboard
```

- PS：
  
  - 进入 sentinel 的容器后，发现里面只有文件结构特别简单，只有 `/bladex/sentinel/app.jar`
  - 且用命令 `ps` 可以看见 sentinel 的默认端口为 8858

- 验证：访问 `http://host:8858/`，登录账号密码都为 sentinel

------

```bash
mkdir /root/local/sentinel
cp /root/my_tar/sentinel-dashboard-1.8.0.jar /root/local/sentinel/
```

------

### Seata

##### 在 docker 上安装

- mysql 上搭建相应数据库
  
  - 创建数据库 seata

- 创建表(可以去官网找到对应版本的文件)

```sql
use seata;
-- -------------------------------- The script used when storeMode is 'db' --------------------------------
-- the table to store GlobalSession data
CREATE TABLE IF NOT EXISTS `global_table`
(
    `xid`                       VARCHAR(128) NOT NULL,
    `transaction_id`            BIGINT,
    `status`                    TINYINT      NOT NULL,
    `application_id`            VARCHAR(32),
    `transaction_service_group` VARCHAR(32),
    `transaction_name`          VARCHAR(128),
    `timeout`                   INT,
    `begin_time`                BIGINT,
    `application_data`          VARCHAR(2000),
    `gmt_create`                DATETIME,
    `gmt_modified`              DATETIME,
    PRIMARY KEY (`xid`),
    KEY `idx_gmt_modified_status` (`gmt_modified`, `status`),
    KEY `idx_transaction_id` (`transaction_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- the table to store BranchSession data
CREATE TABLE IF NOT EXISTS `branch_table`
(
    `branch_id`         BIGINT       NOT NULL,
    `xid`               VARCHAR(128) NOT NULL,
    `transaction_id`    BIGINT,
    `resource_group_id` VARCHAR(32),
    `resource_id`       VARCHAR(256),
    `branch_type`       VARCHAR(8),
    `status`            TINYINT,
    `client_id`         VARCHAR(64),
    `application_data`  VARCHAR(2000),
    `gmt_create`        DATETIME(6),
    `gmt_modified`      DATETIME(6),
    PRIMARY KEY (`branch_id`),
    KEY `idx_xid` (`xid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- the table to store lock data
CREATE TABLE IF NOT EXISTS `lock_table` (
    `row_key` VARCHAR(128) NOT NULL,
    `xid` VARCHAR(128),
    `transaction_id` BIGINT,
    `branch_id` BIGINT NOT NULL,
    `resource_id` VARCHAR(256),
    `table_name` VARCHAR(32),
    `pk` VARCHAR(36),
    `gmt_create` DATETIME,
    `gmt_modified` DATETIME,
    PRIMARY KEY (`row_key`),
    KEY `idx_branch_id` (`branch_id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;
```

- 自定义业务数据库后需要添加表 undo_log

```sql
-- for AT mode you must to init this sql for you business database. the seata server not need it.
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';
```

- 安装镜像（远程部署尚未成功，本地部署可以参考，配置文件的修改基本相同）

```bash
docker search seata
docker pull seataio/seata-server:1.4.2
docker images
```

```bash
docker run --name seata1.4.2 -p 8091:8091 seataio/seata-server
```

- 修改 server 端的配置文件

```bash
docker exec -it seata1.4.2 sh
cd resources/

# 先备份 file.conf、registry.conf
cp file.conf file.conf.bk
cp registry.conf registry.conf.bk

# 修改 file.conf 文件
vi file.conf

store {
  mode = "db"
  ## database store property
  db {
    driverClassName = "com.mysql.cj.jdbc.Driver"
    url = "jdbc:mysql://mysql_host:3306/seata?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false"
    user = "root"
    password = "password"
  }
}

# 修改 registry.conf 文件
vi registry.conf

registry {
  type = "nacos"

  nacos {
    serverAddr = "nacos_host:8848"
    username = "nacos"
    password = "nacos"
  }
}
```

- 查看对应数据库版本，拷贝相应的数据库驱动文件进去

```sql
show variables like "%version%";
```

```bash
# 放入新的数据库驱动，移除旧驱动
docker cp /root/my_tar/mysql-connector-java-8.0.27.jar seata1.4.2:/seata-server/libs
rm /seata-server/libs/mysql-connector-java-5.1.35.jar 
```

- 重启容器 seata1.4.2，在 nacos 上检查是否注册成功

```bash
# 重启
docker stop seata1.4.2 
docker start seata1.4.2 
# 检查日志
docker container logs seata1.4.2 
```

- 注意：
  
  - 数据库的时区应该和其他服务保持一致
  - 远程 seata server 启动时，注册进 nacos 的为内部虚拟端口号，外部访问有问题。在启动时指定为外网端口号 `sh seata-server.sh -h 129.226.227.79 -p 8091`
  - 遇到一个大坑，storage写数据库时一直报 global lock aquire failed，最后删掉数据库重建，莫名其妙搞定

- 其他问题猜测：
  
  - 问题：目前 seata server 只能在本地运行成功，部署到服务器后，order 向 storage 发送了服务请求，storage 执行了服务，但是无法返回给 order
  - 猜测：外网无法联通本地网络（类似上面的 sentinel），服务。。。好像又不对

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

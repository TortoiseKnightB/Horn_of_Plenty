# MyServer 

- 本文档用来记录我的服务器配置信息，以便日后查询

### 偏好设置

- 设置主机名 `vim /etc/hostname`，重启生效
- 设置终端提示符信息 `PS1=[\u@\h \W]\$`

### 目录结构

```
/root/	# 用户名为 root
├── local		# 存放本地应用文件
│   └── mysql	
│
└── my_tar	# 存放压缩包
```

### MySQL

- 官网下载压缩包（Linux 5.7版本）（https://dev.mysql.com/downloads/mysql/）
- 好像需要依赖 libaio 库，阿里云服务器自带，这里就略过

------

// TODO	以下自定义配置失效，暂时略过，以后补充
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
 o0uJrm5%UO>czSveXpg0Mp?e

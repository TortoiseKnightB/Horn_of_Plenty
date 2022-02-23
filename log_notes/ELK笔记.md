# Elasticsearch

- 运行 elasticsearch/bin/elasticsearch.bat 启动服务

### elasticsearch.bat 启动闪退

- windows 打开 cmd，进入 elasticsearch.bat 文件夹下，输入 `elasticsearch.bat` 命令启动
- 查看报错信息，如果为”内存不足“，请修改 config/jvm.options 配置文件

```bash
# 设置 JVM 初始内存为 1G。此值可以设置与-Xmx 相同，以避免每次垃圾回收完成后 JVM 重新分配内存
# Xms represents the initial size of total heap space
# 设置 JVM 最大可用内存为 1G
# Xmx represents the maximum size of total heap space
-Xms1g
-Xmx1g
```

### 登录问题

- elasticsearch8.0 需要用 https 来进行访问访问 https://localhost:9200/
- 用户名为 elastic
- 如果忘记密码，在 bin 目录下执行 `elasticsearch-reset-password -u elastic -i`，重置密码

# Kibana

- 运行 kibana/bin/kibana.bat 启动服务

### 登录问题

- 需要 enrollment 来对 elasticsearch-token 进行验证
- 如果忘记 elasticsearch-token，在 elasticsearch/bin 目录下执行 `elasticsearch-create-enrollment-token -s kibana` 命令，生成新的 token
- 用新 token 进行验证，kibana server 会生成一个6位数的数字密码
- 验证后会要求进行登录，登录账号密码为 elastic 的账号与密码

# Logstash

- 安装后解压，进入 config 文件夹下，添加新的配置文件 logstash-test.conf，配置文件内容为：

```bash
input {
  tcp {
    port => 5044
    type => "test"
    # 将日志内容解析为 json 格式
    codec => json_lines
  }
}

output {
  elasticsearch {
    # txcloud 为自己部署 elasticsearch 的服务器 IP 地址
    hosts => ["https://txcloud:9200"]
    index => "logstash-%{type}"
    user => "elastic"
    password => "elastic"
    ssl => true
    ssl_certificate_verification => false
  }
  stdout{
    # 日志会在本地控制台进行输出
    codec => rubydebug
  }
}
```

- 进入 bin 文件夹，执行命令 logstash -f ../config/logstash-test.conf，启动 logstash（此处为 windows 系统启动，其他应该同理）

# Kibana 查询语法

### 字段名:字段值

- `response:200`：查询出response字段中包含200的文档对象
- `message:hello world`：针对message字段进行搜索，搜索message中包含hello，或者包含world，或者两者都包含的情况。不区分大小写，也不会保证顺序
  - 多个单词，可以用逗号或者空格隔开
- `message:"hello world yes"`：针对message字段进行搜索，在搜索的时候不会区分大小写。"hello world yes"使用了引号，这样的话，这3个单词会被作为一个词进行查询，不会再进行分词
- `name:jane or addr:beijing`：会查询name字段包含jane，或者addr字段包含beijing的记录，或者两者都匹配
- `name:jane and addr:beijing`：查询name字段包含jane，且addr字段包含beijing的记录
- `name:jane and addr:beijing or job:teacher`：KQL中，and的优先级高于or；会查询name包含jane，且addr包含beijing的记录，或者job包含teacher的记录
- `(name:jane and addr:beijing) or job:teacher`：同上
- `name:jane and (addr:beijing or job:teacher)`：可以使用括号来控制匹配的优先级
- `response:(200 or 404)`：查询response包含200，或者response包含404，或者包含200和404的记录（不保证顺序、不区分大小写）
- `not response:200`：查询出response字段中不包含200的记录
- `response:200 and not yes`：查询response包含200，并且**整条记录**(response和其他字段)不包含yes的数据记录

### 通配符

- ?匹配单个字符
- *匹配0或多个字符
- `response:(200 and not yes)`：查询response包含200，且response不包含yes的记录
- `response:*`：返回所有包含response字段的文档对象
- `machine*:hello`：查询machine1字段，machine2字段...machinexyzabc字段包含hello的数据记录

### Lucene 查询语法

- 模糊查询 `~`：在一个单词后面加上~启用模糊搜索
  - first~能匹配到错误的单词frist
- 近似查询 `~`：短语后面加上~，可以搜到被隔开或顺序不同的单词
  - "life movement"~2表示life和movement之间可以隔开2两个词
- 范围查询：`[]` 表示端点数值包含在范围内，`{}` 表示端点不包含在范围内
  - `page: [2 TO 8}` 搜索第2到第8页，包含起始不包含末端
- 优先级查询：如果单词的匹配度很高，一个文档中或者一个字段中可以匹配多次，使用符号 `^` 提高相关度
  - `chenqionghe^2 keep` 、"`chenqionghe keep^2`"
- 逻辑操作
  - `AND`：逻辑与，也可以用 `&&` 代替
  - `OR`：逻辑或，也可以使用 `||` 代替
  - `NOT`：逻辑非，也可以使用 `!` 代替
  - `+`：必须包含
  - `-`：不能包含
    - `muscle AND easy`，muscle和easy必须同时存在
    - `muscle NOT easy`，muscle存在easy不存在
    - `muscle OR easy`，muscle或easy存在
    - `+life -lies`：必须包含life，不包含lies
- 转义特殊字符：`+ - && || ! ( ) { } [ ] ^ " ~ * ? : \`
  - `\(1\+1\)\:2`：用来查询 `(1+1):2`


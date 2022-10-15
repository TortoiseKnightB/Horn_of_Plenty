package com.knight.javadoc;

import com.knight.javadoc.controller.TestController;

/**
 * JavaDoc演示项目
 * <br>
 * 生成JavaDoc文档请另查询网上资料 <a href="https://zhuanlan.zhihu.com/p/369072725">参考文档1</a>，
 * <a href="https://www.cnblogs.com/yanghaolie/p/7029207.html">参考文档2</a>
 * <p>
 * <B>黑粗体</B>
 * <p>
 * &lt;括号的写法 &gt;
 * <p>
 * 链接类{@link com.knight.javadoc.service.TestService TestService}
 * <p>
 * 链接方法{@link Main#main(String[]) main()}
 * <p>
 * operator (&nbsp;+&nbsp;)
 * <p>
 * see <i>The Java&trade; Language Specification</i>.
 * <p>
 * For example:
 * <blockquote><pre>
 *     String str = "abc";
 * </pre></blockquote>
 * <p>
 * is equivalent to:
 * <blockquote><pre>
 *     char data[] = {'a', 'b', 'c'};
 *     String str = new String(data);
 * </pre></blockquote>
 * 画表格
 * <table border>
 * <tr valign=top><th>Input Number</th>
 * <th>Input rounded to one digit<br> with UP rounding</th></tr>
 * <tr align=right><td>5.5</td> <td>6</td>
 * <tr align=right><td>2.5</td> <td>3</td>
 * <tr align=right><td>1.6</td> <td>2</td>
 * <tr align=right><td>1.1</td> <td>2</td>
 * </table>
 * <pre>
 * <ul>
 * <li>a</li>
 * <li>b</li>
 * <li>c</li>
 * </ul>
 * </pre>
 * 写代码或配置文件内容:
 * <pre>{@code
 * <?xml version="1.0"?>
 * <?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
 * <configuration>
 * <property>
 * <name>mapreduce.randomwriter.minkey</name>
 * <value>10</value>
 * </property>
 * <property>
 * <name>mapreduce.randomwriter.maxkey</name>
 * <value>10</value>
 * </property>
 * <property>
 * <name>mapreduce.randomwriter.minvalue</name>
 * <value>90</value>
 * </property>
 * <property>
 * <name>mapreduce.randomwriter.maxvalue</name>
 * <value>90</value>
 * </property>
 * <property>
 * <name>mapreduce.randomwriter.totalbytes</name>
 * <value>1099511627776</value>
 * </property>
 * </configuration>}</pre>
 *
 * @author TortoiseKnightB
 * @version 1.0
 * @implNote This field is trusted by the VM, and is a subject to
 * constant folding if String instance is constant. Overwriting this
 * field after construction will cause problems.
 * @date 2022/10/15
 * @see TestController
 * @since 1.0
 */
public class Main {

    //region 主方法
    public static void main(String[] args) {

    }
    //endregion
}

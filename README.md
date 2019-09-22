# k-sql-script

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://img.shields.io/badge/openjdk-1.8%2B-green.svg)]() ![](https://img.shields.io/badge/release-1.0--GA-brightgreen.svg) ![](https://img.shields.io/badge/coverage-95%25-yellowgreen.svg)

## 工程目的

JDBC是一个阻塞调用的mysql client , 但是在异步编程发展的越来越好的今天 , 阻塞式调用的弊端越来越显著,异步编程和非阻塞式的调用将会是未来的发展趋势

异步非阻塞化的数据库操作将会是下面这种形式

```java
Server.connect(ip,data,con->{   =>异步获取一个con链接
    con.select("sql",para,res->{  => 异步发送一个sql查询
       res.getReult();  => 异步的接收返回值,然后进行业务逻辑处理
       ....
    });
});
```

这种形式我们能发现一个非常特殊的点,就是 结果集和 sql是分离的

这里简单解释一下上面的结论

我们看一些mybatis 或者 hibnate这种orm框架,他们的实现都是将一个sql查询的过程转化成函数调用,函数的返回值就是结果

```java
resultObject = sqlMethod.find(param1,param2);
```

但是在java体系中,因为java没有类似js的 async / await 机制,所以没办法实现函数这种语义的异步回调

k-sql-script 源于mybatis 的sql脚本的思想. 并且将查询过程和结果集转化过程分离,借此来更加方便的实现异步回调方法

## k-sql-script解决的主要问题

k-sql-script主要解决在传统orm框架sql和结果集绑定导致无法进行异步化的问题

## k-sql-script 环境和依赖要求

- java环境
  - java8+,兼容java9+反射模型
- 构建工具
  - gradle5.0+
- 其他依赖
  - 无第三方依赖

## k-sql-script 导入方法

> 1. 直接使用jar导入

在一个release中下载对应版本的jar包,然后导入到项目的classpath目录即可

> 2. 使用maven仓库 或者 gradle

ps: 正在申请域名准备发布到公链上,目前只提供了本地打包的功能

在项目根目录中运行如下命令

```groovy
gradle publishToMavenLocal
```

即可打包到本地仓库,如果需要暂时请自行的添加到私有仓库中

> maven地址:

```xml
<dependency>
    <groupId>org.k-sql</groupId>
    <artifactId>k-sql-script</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

> gradle地址

```groovy
implementation ('org.k-sql:k-sql-script:1.0-SNAPSHOT')
```

## k-sql-script 使用方法

k-sql-script的使用其实是非常容易

k-sql-script的设计是参考mybatis的设计的

k-sql-script的

1. 定义mapper文件

```java
@Mapper
public interface TestMapper {
    @Select("select * from user where name=:name and age=:age")
    int test(@Param("name") String name, @Param("age") String age);
    @Select(value = "select * from user where name=:name and age=:age")
    int test2(Item item);
}
```





## 开源协议和授权

mirror-reflection is Open Source software released under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).
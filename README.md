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

## mirror-reflection解决的主要问题

1. 降低java反射使用难度,能更加容易的编写出优美的反射代码
2. 扩展java反射操作,实现通过名称或者字符串快速操作变量或者运行方法
3. 针对java反射操作进行优化,内部使用缓存机制,提高java反射操作多次调用时候的性能
4. 通过本框架对java反射操作的优化,可以更加方便的集成到其他框架中,方便其他各种框架的开发

## mirror-refllection 环境和依赖要求

- java环境
  - java8+,兼容java9+反射模型
- 构建工具
  - gradle5.0+
- 其他依赖
  - 无第三方依赖

## mirror-relection 导入方法

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
    <groupId>org.mirror</groupId>
    <artifactId>mirror-reflection</artifactId>
    <version>xxx</version>
</dependency>
```

> gradle地址

```groovy
implementation 'org.mirror:mirror-reflection:1.0-SNAPSHOT'
```

## mirror-refllection 使用方法

### 1. reflector和reflectorFactory类

> reflector类

针对java class的一层封装,提供java类的field和method缓存功能

引申:Agent接口-> mirror为了简化操作,将method操作和field操作都简化成一个agent,当需要运行或者取值的时候只需要运行接口的invoke方法即可(相当于针对java反射操作的一层封装)

> 主要方法

方法名称|参数|作用
---|---|---
getGetAgent|propertyName:string|获取对应名称的field get agent(相当于get方法)
getSetAgent|propertyName:string|获取对应名称的field set agent(相当于set方法)
getGetterType|propertyName:string|获取对应名称的field get方法返回值class类型
getSetterType|propertyName:string|获取对应名称的field set方法返回值class类型
getMethod|methodName:string|获取对应名称的方法列表(java支持函数重载,所以返回值是列表)

> 例子:(具体信息请查看test目录下的代码,这是只记录调用方法)

```java
People one = new People();
one.setAge(12);
one.setName("one");
one.setOtherPeopleThingk("bad one");

People two = new People();
one.setAge(13);
one.setName("two");
one.setOtherPeopleThingk("goods one");

GoodsPeople goodsPeople = new GoodsPeople();
goodsPeople.setContury(ConEnum.CHINA);
goodsPeople.setHelpPeopleNumber(1000);
goodsPeople.setMoney(123432.22);
goodsPeople.setFrinds(new People[]{one, two});

Reflector reflector = new Reflector(goodsPeople.getClass());
Agent agent = reflector.getGetAgent("money");
Agent agent1 = reflector.getMethod("whatDoYouThink").get(0);
System.out.println(agent.invoke(goodsPeople, null));
System.out.println(agent1.invoke(goodsPeople, one));
```

> reflectorFacotry类

针对reflect的一层封转简化创建过程,并提供缓存功能

> 主要方法

方法名称|参数|作用
---|---|---
findForClass|type:class|获取class对应的reflector类

> 例子 :

```java
Agent agent = reflector1.getGetAgent("money");
Agent agent1 = reflector.getMethod("whatDoYouThink").get(0);
System.out.println(agent.invoke(goodsPeople, null));
System.out.println(agent1.invoke(goodsPeople, one));
```


### 2. mirrorClass

这个方法是针对reflector的一层封装,支持路径解析方法获取field或者method的反射

-----

> 引申:mirror的路径解析原则

基本语法 

- 非数组或列表或者map的情况 
  - 语句:xxx.yyy.zzz(以下的解释只是方便理解,实际上并不是采用这种操作)
    - 当要解析的是变量信息时 mirror框架拿到getXxx().getYyy().getZzz()对应的参数反射
    - 当要解析的是方法时 mirror框架将会拿到getXxx().getYyy().zzz()的method反射
- 数组或列表或者map的情况
  - 语句xxx.yyy[ppp].zzz
    - mirror框架的即系原则和上面类似,总结一下就是可以拿到对应路径的下的变量反射或者函数反射

注意 mirrorclass的路径解析其实是有局限性的,表现在xxx.yyy[ppp].zzz这种情况下,其实是获取不到集合数组对应的反射的,原因是因为这种情况下即使拿到了,也无法使用java自身的反射机制,成功调用
其次是java的泛型使用的是类型擦除模式,即使是拿到了也是object类型,无法进行反射,所以在这里针对集合类不提供这种功能
-----

> 主要方法

> ps: 相比较reflector支持路径解析

方法名称|参数|作用
---|---|---
getGetAgent|propertyName:string|获取对应名称的field get agent(相当于get方法)
getSetAgent|propertyName:string|获取对应名称的field set agent(相当于set方法)
getGetterType|propertyName:string|获取对应名称的field get方法返回值class类型
getSetterType|propertyName:string|获取对应名称的field set方法返回值class类型
getMethod|methodName:string|获取对应名称的方法列表(java支持函数重载,所以返回值是列表)

> 例子

```java
MirrorClass mirrorClass = MirrorClass.forClass(GoodsPeople.class);
System.out.println(mirrorClass.getGetAgent("frinds"));
```

### 3. mirrorObject

一个object对象的代理通过这个方法可以更加方便的使用反射+路径解析的方式操作这个object对象

> 主要方法

方法名称|参数|作用
---|---|---
getValue|name:string,item:class|获取名称微string类型为item类型的值
setValue|name:string,value:object|为名称微name的对象赋value值
invoke|name:string,item:class,params:Object[]|运行名称为name,返回值为item(可以为null),参数为params的方法

```java
MirrorObject mirrorObject = MirrorObject.forObject(goodsPeople);
mirrorObject.setValue("frinds[0].name","change");
System.out.println(mirrorObject.getValue("frinds[0].name",String.class));
mirrorObject.invoke("frinds[0].doSay");
```

## 开源协议和授权

mirror-reflection is Open Source software released under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).
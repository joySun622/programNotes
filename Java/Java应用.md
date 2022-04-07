[Toc]

# 连接Mysql数据库报错

- **场景描述**

> 连接MySQL数据库报错，报错日志如下：
>
> ```
> java.sql.SQLException: java.lang.ClassCastException: java.math.BigInteger cannot be cast to java.lang.Long
> 	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:1074)
> 	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:988)
> 	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:974)
> 	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:919)
> ```

- **原因探索**

> 排除代码中数据类型转换问题。连接本地数据库没有问题，连接生产数据库报错。原因：mysql-connector-java 引用的驱动jar包版本导致。本地数据库版本为：5.0版本，使用的mysql-connector-java 的版本是5.5.25，没有问题。生产上的mysql数据库版本为8.0.11，java程序中引用的mysql-connector-java版本过低，导致连接数据库失败。

- **解决方案**

> 升级mysql-connector-java jar包版本到8.0.11。升级版本后使用正常

```
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>8.0.11</version>
</dependency>
```


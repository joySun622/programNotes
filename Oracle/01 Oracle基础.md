[Toc]

# 数据库简介

- **数据库**：存储数据的仓库。
- **数据库种类**：
  1. 小型数据库：access;
  2. 中型的数据库：MySQL,SqlServer;
  3. 大型的数据库：Oracle,DB2,Sybase,达梦(国产)

- **Oracle** 数据库版本：
  9i    internet 网络
  10G  11G 网格
  12C  clound云技术，暂不成熟
- **默认的三个用户**
  1. sys 密码安装时设定，超级管理员；
  2. system 密码安装时设定，管理其他用户；
  3. scott  tiger   默认被锁定，普通用户

# Oracle语句处理顺序

# SELECT语句的处理过程

我们知道，SQL 查询的大致语法结构如下：

```
(5)SELECT DISTINCT TOP(<top_specification>) <select_list>                     

(1)FROM <left_table> <join_type> JOIN <right_table> ON <on_predicate>

(2)WHERE <where_predicate>

(3)GROUP BY <group_by_specification>

(4)HAVING <having_predicate>

(6)ORDER BY <order_by_list>
```

**SELECT语法的处理顺序**：

The following steps show the processing order for a SELECT statement.

1. FROM
2. ON
3. JOIN
4. WHERE
5. GROUP BY
6. WITH CUBE or WITH ROLLUP
7. HAVING
8. SELECT
9. DISTINCT
10. ORDER BY
11. TOP
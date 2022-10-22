[Toc]

# MySQL说明文档使用

## 1. 定位到指定版本说明文档

[MySQL官方说明文档](https://dev.mysql.com/doc/)

1. 在官方文档首页，可以看到MySQL最新版本说明文档，直接点击，即可进入该版本的指导手册页面

![image-20220422172129559](images/image-20220422172129559.png)

![image-20220422172244004](images/image-20220422172244004.png)

2. **查看MySQL以前版本说明文档，在【说明文档】首页，【MySQL Server】下可以查看到提供的以前版本的MySQL**

   ![image-20220422172509204](images/image-20220422172509204.png)

   ![image-20220422173057770](images/image-20220422173057770.png)

## 2. 定向查找

> 当不熟悉文档目录时，在指定说明文档，搜索需要查看内容的关键字。指定查看内容比较有效率。
>
> 如果对文档比较熟，可以到对应目录下查看

![image-20220422173257723](images/image-20220422173257723.png)

# 查询某一指定字段值数量

> [`COUNT(expr) over_clause`](https://dev.mysql.com/doc/refman/8.0/en/aggregate-functions.html#function_count)
>
> Returns a count of the number of non-`NULL` values of *`expr`* in the rows retrieved by a [`SELECT`](https://dev.mysql.com/doc/refman/8.0/en/select.html) statement. The result is a [`BIGINT`](https://dev.mysql.com/doc/refman/8.0/en/integer-types.html) value.
>
> If there are no matching rows, [`COUNT()`](https://dev.mysql.com/doc/refman/8.0/en/aggregate-functions.html#function_count) returns `0`.
>
> 说明：count(表达式)，返回的是满足表达式条件非null 整数值记录数；当没有匹配的行时，返回0

```
### 场景描述：统计类型值为1，以及为0数据量；当没有该类型记录时，返回0
select 
    count(case when type=1 then type end) into_village_num, 
    count(case when type=0 then type end) not_into_village_num,
    express_nam express_name 
from t_village group by express_nam

执行结果：
into_village_num   not_into_village_num  express_name
226					0						百世
273					4						中通
246					2						圆通
215					0						申通
242					0						韵达
117					0						极兔

### 统计指定字段值的数量
select 
	sum(case when mrs.字段名='1' then 1 else 0 end) accomplish,
	sum(case when mrs.字段名='0' then 1 else 0 end) unfinished
from
	表名 mrs
```

# Insert语句中使用UUID

如果一个表中id字段使用uuid来作为主键，那我们可以使用下面的语句来插入数据：

```
INSERT INTO t_inventive_principle (`id`,`code_num`,`name`) VALUES (REPLACE(UUID(),"-",""),1,'分割原理');
```

# mysql AES加密的使用

- **加密**

```
INSERT INTO aes_demo (NAME, PASSWORD)
VALUES
    (
        'tom',
        HEX(AES_ENCRYPT('666', '1'))
    );
```

- **相应的解密查询如下**：

```
SELECT
    AES_DECRYPT((UNHEX(PASSWORD)), '1') AS PASSWORD
FROM
    aes_demo;
```

# 合并查询结果`UNION&UNION ALL`

## 区别

> 如果我们需要将两个 `select` 语句的结果作为一个整体显示出来，我们就需要用到 `union` 或者 `union all` 关键字。`union` (或称为联合)的作用是将多个结果合并在一起显示出来。
>
> `union` 和 `union all `的区别是，union 会自动压缩多个结果集合中的重复结果，而 union all 则将所有的结果全部显示出来，不管是不是重复。
>
> `union`：对两个结果集进行并集操作，不包括重复行，同时进行默认规则的排序；union 在进行表链接后会筛选掉重复的记录，所以在表链接后会对所产生的结果集进行排序运算，删除重复的记录再返回结果。实际大部分应用中是不会产生重复的记录，最常见的是过程表与历史表 union。
>
> 如下sql：
>
> ```
> SELECT create_time FROM `e_msku_sku` WHERE msku = '21-BQLEDNL120W-BK'
> UNION
> SELECT create_time FROM `e_msku_sku` WHERE msku = '21-BQLEDNL120W-BK'
> ```
>
> ![001.png](images/f2e1f5a979621ec5d1c63a2368b9b45a.png)
>
> 
>
> **`union all`**：对两个结果集进行并集操作，包括重复行，不进行排序； 如果返回的两个结果集中有重复的数据，那么返回的结果集就会包含重复的数据了。
>
> ```
> SELECT create_time FROM `e_msku_sku` WHERE msku = '21-BQLEDNL120W-BK'
> UNION ALL
> SELECT create_time FROM `e_msku_sku` WHERE msku = '21-BQLEDNL120W-BK'
> ```
>
> ![002.png](images/960f67726a0d5324ddcdc1ea36ecf22b.png)

## 注意事项

> 1. **UNION 和 UNION ALL 内部的 SELECT 语句必须拥有相同数量的列**;
>    ![image.png](images/a7c3139a25e5336a9a24a15e2f4d3a8c.png)
>
> 2. **每条 SELECT 语句中列的顺序必须相同**
>
>    - 先来说下，如果顺序不同，会是什么结果？
>      答：结果字段的顺序以**union all** 前面的表字段顺序为准。
>      **union all** 后面的表的数据会按照顺序依次附在后面。**注意：按照字段顺序匹配，而不是按照字段名称匹配。**
>
>    - sql如下：顺序对结果的影响
>      ![image.png](images/a6c4b5b8381193eb7c0d6cbb3c8eaf13.png)综上：
>
>      **union all** 结果字段的顺序以 **union all** 前面的表字段顺序为准。**union all** 后面的表的数据会按照字段顺序依次附在后面，而不是按照字段名称匹配
>      我们上面以*来表示顺序的不同，其实你写成不同顺序的字段结果一致
>      ![image.png](images/9cfda41c3c5082f810d986afe7e3e26c.png)

## union all 使用场景

> - sql 中的组合in，可用 union all 来代替，提高查询效率
>
> - **修改前：组合in sql**
>
>   ```
>   SELECT ***, ***, ***, ***, ***
>   FROM e_rating_info 
>   WHERE rating_quantity <> 0 AND (***, ***) 
>   IN (('***', '***'), ('***', '***'), 
>   ('***', '***'), ('***', '***'), 
>   ('***', '***'), ('***', '***'), 
>   ('***', '***'), ('***', '***'), 
>   ('***', '***'), ('***', '***')) 
>   ORDER BY *** DESC
>   ```
>
> - **修改后：UNION ALL sql**
>
>   ```
>   <select id="queryRatingInfo" resultType="***">
>           <foreach collection="ratingList" item="item" index="index" open="" separator="UNION ALL" close="">
>               SELECT ***, ***, ***, ***, ***
>               FROM e_rating_info
>               WHERE rating_quantity &lt;&gt; 0
>               AND country_code = #{item.***}
>               AND asin = #{item.***}
>           </foreach>
>           ORDER BY *** DESC;
>       </select>
>   ```

# 统计一列中不同值的数量方法

## 方案1

> 使用`where`条件限定查询

```
SELECT count(*)
FROM user_operation_log
WHERE origin = 'iPhone';
SELECT count(*)
FROM user_operation_log
WHERE origin = 'Android';
SELECT count(*)
FROM user_operation_log
WHERE origin = 'Web';
```

## 方案2

> **用 count 实现**统计 user_operation_log 有多少行：
>
> ```
> SELECT count(*) FROMuser_operation_log
> ```
>
> 统计 origin 这列值不为 NULL 的数量：
>
> ```
> SELECT count(origin) FROMuser_operation_log
> ```

```
SELECT
 count(origin = 'iPhone' OR NULL) AS iPhone,
 count(origin = 'Android' OR NULL) AS Android,
 count(origin = 'Web' OR NULL)  AS Web
FROM user_operation_log;
```

## 方案3

```
SELECT
 sum(if(origin = 'iPhone', 1, 0)) AS iPhone,
 sum(if(origin = 'Android', 1, 0)) AS Android,
 sum(if(origin = 'Web', 1, 0))  AS Web
FROM user_operation_log;
```

## 方案4

```
SELECT
 sum(origin = 'iPhone') AS iPhone,
 sum(origin = 'Android') AS Android,
 sum(origin = 'Web')  AS Web
FROM user_operation_log;
```

## 方案5

```
SELECT origin,count(*) num FROM user_operation_log GROUP BY origin;
```



# 参考资料来源

1. https://www.cnblogs.com/silenceshining/p/14916119.html
2. https://blog.csdn.net/t194978/article/details/123490979
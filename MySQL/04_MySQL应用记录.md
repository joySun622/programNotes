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
```


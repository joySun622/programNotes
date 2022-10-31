[Toc]

# MySQL执行计划

> [官网执行计划优化说明](https://dev.mysql.com/doc/refman/5.7/en/execution-plan-information.html)

## 作用范围

> 1. [`EXPLAIN`](https://dev.mysql.com/doc/refman/5.7/en/explain.html) works with [`SELECT`](https://dev.mysql.com/doc/refman/5.7/en/select.html), [`DELETE`](https://dev.mysql.com/doc/refman/5.7/en/delete.html), [`INSERT`](https://dev.mysql.com/doc/refman/5.7/en/insert.html), [`REPLACE`](https://dev.mysql.com/doc/refman/5.7/en/replace.html), and [`UPDATE`](https://dev.mysql.com/doc/refman/5.7/en/update.html) statements；
>
> 2. [`EXPLAIN`](https://dev.mysql.com/doc/refman/5.7/en/explain.html) can also be used to obtain information about the columns in a table. [`EXPLAIN *`tbl_name`*`](https://dev.mysql.com/doc/refman/5.7/en/explain.html) is synonymous with `DESCRIBE *`tbl_name`*` and `SHOW COLUMNS FROM *`tbl_name`*`.
>
>    ```
>    - 范例1：获取SELECT执行计划
>    
>    mysql> EXPLAIN SELECT * FROM EMP;
>    +----+-------------+-------+------------+------+---------------+------+---------+------+------+------
>    | id |select_type| table| partitions|type| possible_keys |key | key_len | ref | rows |filtered|Extra|
>    +----+-------------+-------+------------+------+---------------+------+---------+------+------+------
>    |  1 | SIMPLE    | EMP   | NULL   | ALL  | NULL | NULL | NULL    | NULL |   15 |   100.00 | NULL  |
>    +----+-------------+-------+------------+------+---------------+------+---------+------+------+------
>    1 row in set (0.03 sec)
>    
>    - 范例2：获取表列信息
>    mysql> EXPLAIN EMP;
>    +----------+-------------+------+-----+---------+-------+
>    | Field    | Type        | Null | Key | Default | Extra |
>    +----------+-------------+------+-----+---------+-------+
>    | EMPNO    | int(4)      | NO   | PRI | NULL    |       |
>    | ENAME    | varchar(10) | YES  | UNI | NULL    |       |
>    | JOB      | varchar(9)  | YES  |     | NULL    |       |
>    | MGR      | int(4)      | YES  |     | NULL    |       |
>    | HIREDATE | date        | YES  |     | NULL    |       |
>    | SAL      | double(7,2) | YES  |     | NULL    |       |
>    | COMM     | double(7,2) | YES  |     | NULL    |       |
>    | DEPTNO   | int(2)      | YES  |     | NULL    |       |
>    +----------+-------------+------+-----+---------+-------+
>    ```

## EXPLAIN 输出信息格式

> 1. 每一行都是一个表的执行信息，表信息显示的先后顺序为表在执行查询操作中扫描的顺序；
> 2. 执行计划输出的列说明：第一列为表查询一般字段名，第二列为当输出格式设置为 `FORMAT=JSON` 字段名，与第一列字段含义是相同的。
>
> | Column        | JSON Name     | Meaning                                        |
> | ------------- | ------------- | ---------------------------------------------- |
> | id            | select_id     | The SELECT identifier                          |
> | select_type   | None          | The SELECT type                                |
> | table         | table_name    | The table for the output row                   |
> | partitions    | partitions    | The matching partitions                        |
> | type          | access_type   | The join type                                  |
> | possible_keys | possible_keys | The possible indexes to choose                 |
> | key           | key           | The index actually chosen                      |
> | key_len       | key_length    | The length of the chosen key                   |
> | ref           | ref           | The columns compared to the index              |
> | rows          | rows          | Estimate of rows to be examined                |
> | filtered      | filtered      | Percentage of rows filtered by table condition |
> | Extra         | None          | Additional information                         |

### id(select_id)

> select 查询序号，包含一组数字，表示查询中执行select子句或者操作表的顺序
> The [`SELECT`](https://dev.mysql.com/doc/refman/8.0/en/select.html) identifier. This is the sequential number of the [`SELECT`](https://dev.mysql.com/doc/refman/8.0/en/select.html) within the query. The value can be `NULL` if the row refers to the union result of other rows. In this case, the `table` column shows a value like `<union*`M`*,*`N`*>` to indicate that the row refers to the union of the rows with `id` values of *`M`* and *`N`*.
>
> SELECT标识符，它是查询里的SELECT的顺序编号。如果行关联其他行结果时，id值可能为null。此时table字段显示的值为``<union*`M`*,*`N`*>` `,表示该行结果为id=m与id=n联合结果。

1. **id相同，那么执行顺序从上到下**

```
mysql> EXPLAIN SELECT * FROM EMP E JOIN DEPT D ON E.DEPTNO=D.DEPTNO JOIN SALGRADE SG  ON E.SAL BETWEEN SG.LOSAL AND SG.HISAL;
```

![image-20211020141320957](images/image-20211020141320957.png)

2. **id不同**，如果是子查询，id序号会递增，id值越大优先级越高，越先被执行

   ```
   EXPLAIN SELECT E.ENAME FROM EMP E WHERE E.DEPTNO=(SELECT D.DEPTNO FROM DEPT D WHERE COMM IS NOT NULL);
   ```

   ![image-20211020151231448](images/image-20211020151231448.png)

   ```
   mysql> EXPLAIN SELECT E.ENAME,E.DEPTNO FROM EMP E UNION SELECT D.DEPTNO,D.DNAME FROM DEPT D;
   ```

   ![image-20211020143651464](images/image-20211020143651464.png)

3. **id相同和不同的，同时存在**：相同的可以认为是一组，从上往下顺序执行，在所有组中，id值越大，优先级越高，越先执行

   ```
   mysql> EXPLAIN SELECT * FROM EMP E JOIN DEPT D ON E.DEPTNO=D.DEPTNO JOIN SALGRADE SG ON E.SAL BETWEEN SG.LOSAL AND SG.HISAL WHERE E.DEPTNO =(SELECT D.DEPTNO FROM DEPT D WHERE D.DNAME='SALES');
   ```

   ![image-20211020151518006](images/image-20211020151518006.png)

#### IN & = 执行计划区别

![image-20211020152124630](images/image-20211020152124630.png)

![image-20211020151821647](images/image-20211020151821647.png)

### select_type(JSON name: none)

| select_type Value    | JSON Name                  | Meaning                                                      |
| -------------------- | -------------------------- | ------------------------------------------------------------ |
| SIMPLE               | None                       | Simple SELECT (not using UNION or subqueries)                |
| PRIMARY              | None                       | Outermost SELECT                                             |
| UNION                | None                       | Second or later SELECT statement in a UNION                  |
| DEPENDENT UNION      | dependent (true)           | Second or later SELECT statement in a UNION, dependent on outer query |
| UNION RESULT         | union_result               | Result of a UNION.                                           |
| SUBQUERY             | None                       | First SELECT in subquery                                     |
| DEPENDENT SUBQUERY   | dependent (true)           | First SELECT in subquery, dependent on outer query           |
| DERIVED              | None                       | Derived table                                                |
| MATERIALIZED         | materialized_from_subquery | Materialized subquery                                        |
| UNCACHEABLE SUBQUERY | cacheable (false)          | A subquery for which the result cannot be cached and must be re-evaluated for each row of the outer query |
| UNCACHEABLE UNION    | cacheable (false)          | The second or later select in a UNION that belongs to an uncacheable subquery (see UNCACHEABLE SUBQUERY) |

(1) SIMPLE(简单SELECT，不使用UNION或子查询等)

(2) PRIMARY(子查询中最外层查询，查询中若包含任何复杂的子部分，最外层的select被标记为PRIMARY)

(3) UNION(UNION中的第二个或后面的SELECT语句)

(4) DEPENDENT UNION(UNION中的第二个或后面的SELECT语句，取决于外面的查询)

(5) UNION RESULT(UNION的结果，union语句中第二个select开始后面所有select)

(6) SUBQUERY(子查询中的第一个SELECT，结果不依赖于外部查询)

(7) DEPENDENT SUBQUERY(子查询中的第一个SELECT，依赖于外部查询)

(8) DERIVED(派生表的SELECT, FROM子句的子查询)

(9) UNCACHEABLE SUBQUERY(一个子查询的结果不能被缓存，必须重新评估外链接的第一行)

### `table` (JSON name: `table_name`)

> - The name of the table to which the row of output refers. This can also be one of the following values:
>   - `<union*`M`*,*`N`*>`: The row refers to the union of the rows with `id` values of *`M`* and *`N`*.
>   - `<derived*`N`*>`: The row refers to the derived table result for the row with an `id` value of *`N`*. A derived table may result, for example, from a subquery in the `FROM` clause.
>   - `<subquery*`N`*>`: The row refers to the result of a materialized subquery for the row with an `id` value of *`N`*. See [Section 8.2.2.2, “Optimizing Subqueries with Materialization”](https://dev.mysql.com/doc/refman/5.7/en/subquery-materialization.html).

### `type` (JSON name: `access_type`)

> 关联类型或者访问类型:对表访问方式，表示MySQL在表中找到所需行的方式
>
> **查询效率如下**：
>
> `system > const > eq_ref > ref > fulltext > ref_or_null > index_merge > unique_subquery > index_subquery > range > index > ALL`
>
> **常用的类型有**： **ALL、index、range、 ref、eq_ref、const、system、**NULL（从左到右，性能从差到好
>
> **ALL**：Full Table Scan， MySQL将遍历全表以找到匹配的行
>
> **index**: Full Index Scan，index与ALL区别为index类型只遍历索引树
>
> **range**:只检索给定范围的行，使用一个索引来选择行
>
> **ref**: 表示上述表的连接匹配条件，即哪些列或常量被用于查找索引列上的值
>
> **eq_ref**: 类似ref，区别就在使用的索引是唯一索引，对于每个索引键值，表中只有一条记录匹配，简单来说，就是多表连接中使用primary key或者 unique key作为关联条件
>
> **const、system**: 当MySQL对查询某部分进行优化，并转换为一个常量时，使用这些类型访问。如将主键置于where列表中，MySQL就能将该查询转换为一个常量，system是const类型的特例，当查询的表只有一行的情况下，使用system
>
> **NULL**: MySQL在优化过程中分解语句，执行时甚至不用访问表或索引，例如从一个索引列里选取最小值可以通过单独索引查找完成。

### `possible_keys` (JSON name: `possible_keys`)

> 显示可能应用在这张表中的索引，一个或多个。
> 查询涉及到的字段上若存在索引，则该索引将被列出，但不一定被查询实际使用

### `key` (JSON name: `key`)

> 实际使用的索引。如果为NULL，则没有使用索引
>
> 查询中若使用了覆盖索引，则该索引和查询的selet字段重叠，仅出现在key列表中。
> 覆盖索引：查询的字段与所建索引的字段个数和顺序刚好吻合

### `ref` (JSON name: `ref`)

> 列与索引的比较

### `rows` (JSON name: `rows`)

> 扫描出的行数(估算的行数)

### `filtered` (JSON name: `filtered`)

> 按表条件过滤的行百分比

### `Extra` (JSON name: none)

> 执行情况的描述和说明

# 索引

> 高效获取数据的排好序的数据结构

- **索引数据结构**
  1）HASH;
  2)  二叉树
  3）红黑树
  4）B+树

## 二叉树(Binary tree)

> - **特点**

## 哪些字段适合建立索引

> 1. 表的主键，外键
> 2. 针对数据量大，且查询比较频繁的表建立索引
> 3. 针对常作为查询条件(where),排序(order by)，分组(group by)操作的字段建立索引
> 4. 尽量选择区分度高的列作为索引，尽量建立唯一索引，区分度越高，使用索引的效率越高
> 5. 如果是字符串类型的字段，字段的长度较长，可以针对字符串的特点，建立前缀索引
> 6. 尽量使用联合索引，减少单列索引，查询时，联合索引很多时候可以索引覆盖，避免回表，提高查询效率
> 7. 要控制索引的数量，索引过多，维护索引结构的代价很大，会影响增删改的效率
> 8. 经常与其他表进行连表查询，在连接字段上可以建立索引
> 9. 索引应该建立在小字段上，对于大的文本字段不要建立索引
> 10. 如果索引列不能存储null值，则在建表时加入not null约束。当优化器知道每列是否包含null值时，它可以更好地确定哪个索引最有效地用于查询。
>
> ## 数据库建立索引的常用规则如下：
>
> 1.表的**主键和外键**建立索引【主键具有唯一性，索引值也是有唯一，查询时可以快速定位到数据行；外键一般关联的是另一个表的主键，所以在多表查询时也可以快速定位。】
>
> 2.在order by 或 group by 或 count/max 后面的字段（**分组字段或排序字段或统计字段**）建立索引
>
> 3.**数据量超过300**的应该建立索引
>
> 4.索引应该建在小字段上【**占用存储空间少**的字段更适合选作索引的关键字，例如，与字符串相比，整型字段占用的存储空间较少，因此整型较为适合选作索引。】 
>
> 5.**较长的字符串**则使用**前缀索引**，**定义合适的长度，就可以节省空间又不用额外增加太多的查询成本**。
>
> 6.**存储空间固****定**的字段更适合选作索引的关键字。【与 text 类型的字段相比，char 类型的字段较为适合选作索引关键字。】
>
> 7.经常与其他表进行连接的表的字段，应该在该**连接字段**上建立索引
>
> 8.经常出现在**where子句中的字段**应该建立索引，特别是大表字段（频繁作为查询条件的字段）
>
> 9.索引应建立在选择性较高（离散度）的字段（索引不重复的值和表记录数N的比值，范围从1/N 到 1 ，比如性能最高的**唯一索引的选择性是 1**） 
>
> 10.尽量地扩展索引，不要新建索引。比如表中已经有a的索引，现在要加(a,b)的索引，则只要修改原来的索引即可（**修改为复合索引，不要新增**）
>
> 11.复合索引在建立时应该具体分析，**尽量用单字段索引替代**
>
> 1）、正确选择复合索引中的**主列**（靠近左边的字段），一般是选择性较好的字段；
>
> 2）、复合索引的几个字段是否经常同时以AND方式出现在Where子句中？单字段查询是否极少甚至没有？如果是，则可以建立复合索引；否则考虑单字段索引；
>
> 3）、如果复合索引中包含的字段经常**单独出现在Where子句**，则分解为多个单字段索引；
>
> 4）、如果复合索引所包含的**字段超过3个**，那么仔细考虑其必要性，考虑**减少复合**的字段；
>
> 5）、如果既有单字段索引，又有这几个字段上的复合索引，一般可以**删除复合索引**。
>
> ## 不应该建立索引的字段规则
>
> 1.不应该在**字段比较长**的字段上建立索引，因为会消耗大量的空间
>
> 2.对于**频繁更新、插入的字段应该少建立索引**，因为在修改和插入之后，数据库会去维护索引，会消耗资源
>
> 3.尽量少在**无用字段**上建立索引【where条件中用不到的字段】
>
> 4.**表记录太少**不应该创建索引
>
> 5.**数据重复且分布平均**的表字段不应该创建索引【选择性太低，例如性别、状态、真假值等字段】
>
> 6.**参与列计算**的列不适合建索引【保持列"干净"，比如from_unixtime(create_time) = '2014-05-29'就不能使用到索引，原因是**b+树中存的都是数据表中的字段值**，但进行检索时需要把**所有元素都应用函数才能比较，显然成本太大**，所以语句应该写成create_time = unix_timestamp('2014-05-29')】
>
>  

# 优化

## count(*) & count(1) & count(字段)区别

> `count(*)`、`count(主键id)` 和 `count(1)` 都表示返回满足条件的结果集的总行数；而`count(字段)`则表示返回满足条件的数据行里面，参数`“字段”`不为 `NULL` 的总个数
>
> 效率排序，`count(字段)` < `count(主键id)`< `count(1)` ≈ `count(*)`;尽量使用 `count(*)`

| 函数名          | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| `count(*)`      | `count(*)` 是例外，优化器专门对其做了优化，并不会把全部字段取出来，而是直接按行累加 |
| `count(1)`      | 对于 `count(1)` 来说，`InnoDB` 引擎遍历整张表，但不取值。`server` 层对于返回的每一行，<br />放一个数字 `“1”` 进去，判断是不可能为空的，按行累加 |
| `count(主键id)` | 对于 `count(主键id)` 来说，`InnoDB` 引擎会遍历整张表，把每一行的 `id` 值都取出来，<br />返回给 `server` 层。`server` 层拿到 `id` 后，判断 `id` 不为 `NULL` 的，就按行累加 |
| `count(字段)`   | 如果这个 `“字段”` 定义为 `not null`，一行行地从记录里面读出这个字段，<br />判断不可能为 `null`，按行累加 如果这个 `“字段”` 定义为 `null`，那么执行的时候，<br />判断到有可能是 `null`，还要把值取出来再判断一下，不是 `null` 才累加 |

# mysql复合索引

> 联合索引又叫复合索引。对于复合索引：Mysql从左到右的使用索引中的字段，一个查询可以只使用索引中的一部份，但只能是最左侧部分(最左匹配原则)。例如索引是key index （a,b,c）。 可以支持a | a,b| a,b,c 3种组合进行查找，但不支持 b,c进行查找 .当最左侧字段是常量引用时，索引就十分有效。

# 参考资料

1. https://dev.mysql.com/doc/refman/5.7/en/using-explain.html
2. https://www.bilibili.com/video/BV1pq4y1d7a4?p=2&spm_id_from=pageDriver
3. https://blog.csdn.net/weixin_44390164/article/details/120320088
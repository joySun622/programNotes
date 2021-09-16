[toc]

### 简单说一下数据库的三范式？

第一范式：数据库表的每一个字段都是不可分割的
第二范式：数据库表中的非主属性只依赖于主键
第三范式：不存在非主属性对关键字的传递函数依赖关系

## 事务

### 什么是事务

(事务是恢复和并发控制的基本单位) 就是被绑定在一起作为一个逻辑工作单元的 SQL 语句分组，如果任何一个语句操作失败那么整个操作就被失败，以后操作就会回滚到操作前状态，或者是上有个节点。为了确保要么执行，要么不执行，就可以使用事务。要将有组语句作为事务考虑，就需要通过 ACID 测试，即原子性，一致性，隔离性和持久性。 事务的作用就是保证数据的一致性、完整性。事务隔离级别越高，在并发下会产生的问题就越少，但同时付出的性能消耗也将越大，因此很多时候必须在并发性和性能之间做一个权衡。所以设立了几种事务隔离级别，以便让不同的项目可以根据自己项目的并发情况选择合适的事务隔离级别，对于在事务隔离级别之外会产生的并发问题，在代码中做补偿。

##### 事务的四个基本特征

**原子性：**整个事务中的所有操作，要么全部完成，要么全部不完成，不可能停滞在中间某个环节。事务在执行过程中发生错误，会被回滚（Rollback）到事务开始前的状态，就像这个事务从来没有执行过一样。
**一致性：**在事务开始之前和事务结束以后，数据库的完整性约束没有被破坏。
**隔离性：**隔离状态执行事务，使它们好像是系统在给定时间内执行的唯一操作。如果有两个事务，运行在相同的时间内，执行 相同的功能，事务的隔离性将确保每一事务在系统中认为只有该事务在使用系统。这种属性有时称为串行化，为了防止事务操作间的混淆，必须串行化或序列化请 求，使得在同一时间仅有一个请求用于同一数据。
**持久性：**在事务完成以后，该事务所对数据库所作的更改便持久的保存在数据库之中，并不会被回滚。

### 并发下的问题

**脏读**
​ 脏读是指在一个事务处理过程里读取了另一个未提交的事务中的数据。例如：某个事务已更新一份数据，另一个事务在此时读取了同一份数据，由于某些原因，前一个回滚了操作，则后一个事务所读取的数据就会是不正确的脏数据。
**不可重复读**
​ 不可重复读是指在对于数据库中的某个数据，一个事务范围内多次查询却返回了不同的数据值，这是由于在查询间隔，被另一个事务修改并提交了。
​ 例如：事务A在读取某一数据，而事务B立马修改了这个数据并且提交事务给数据库，事务A再次读取该数据就得到了不同的结果，发生了不可重复读。
**幻读**
​ 在一个事务的两次查询中数据笔数不一致，是事务非独立执行时发生的一种现象。
​ 例如：有一个事务查询了几列数据，而另一个事务却在此时插入了新的几列数据。先前的事务在接下来的查询中，就会发现有几列数据是它先前所没有的。
**对比理解**

- 不可重复读和脏读的区别是：脏读是某一事务读取了另一个事务未提交的脏数据，而不可重复读则是读取了前一事务提交的数据（读取前一事务提交的数据一些场景下会出现问题）。
- 幻读和不可重复读都是读取了另一条已经提交的事务，所不同的是不可重复读查询的都是同一个数据项，而幻读针对的是一批数据整体。

### **谈一谈数据库事务的隔离级别？**

1、Read uncommitted（读未提交）在该隔离级别，所有事务都可以看到其它事务未提交的内容数据。此隔离级别没有解决任何并发问题，不常用。
2、Read committed（读提交）在该隔离级别，一个事务只能读取其它事务已经提交的内容数据。此隔离级别解决了脏读，但没有解决不可重复读和幻读，是ORACLE的默认隔离级别。
3、Repeatable read（重复读）在该隔离级别，能保证一个事务之间的多个实例在并发下能读取同一数据。此隔离级别解决了脏读和不可重复读，是MYSQL的默认级别。
4、Serializable（**可串行化**）这是最高的隔离级别，在此隔离级别，事务事务之间只能顺序执行，使之没有任何冲突。序列化解决了脏读，不可重复读和幻读。
隔离级别越高，越能保证数据的完整性和一致性，但是对并发的效率就越低。因此并不是隔离级别越高越好，应根据具体的业务场景选用合适的事务隔离级别。

## 视图

### 什么是视图？

视图是一种虚拟的表，具有和物理表相同的功能。可以对视图进行增，改，查，操作，试图通常是有一个表或者多个表的行或列的子集。对视图的修改不影响基本表。它使得我们获取数据更容易，相比多表查询。
**如下两种场景一般会使用到视图：**
（1）不希望访问者获取整个表的信息，只暴露部分字段给访问者，所以就建一个虚表，就是视图。
（2）查询的数据来源于不同的表，而查询者希望以统一的方式查询，这样也可以建立一个视图，把多个表查询结果联合起来，查询者只需要直接从视图中获取数据，不必考虑数据来源于不同表所带来的差异。
注：这个视图是在数据库中创建的 而不是用代码创建的。

## 触发器

### **触发器的作用？**

触发器是一中特殊的存储过程，主要是通过事件来触发而被执行的。它可以强化约束，来维护数据的完整性和一致性，可以跟踪数据库内的操作从而不允许未经许可的更新和变化。可以联级运算。如，某表上的触发器上包含对另一个表的数据操作，而该操作又会导致该表触发器被触发。

### **维护数据库的完整性和一致性，你喜欢用触发器还是自写业务逻辑？为什么？**

尽可能使用约束，如 check, 主键，外键，非空字段等来约束，这样做效率最高，也最方便。其次是使用触发器，这种方法可以保证，无论什么业务系统访问数据库都可以保证数据的完整新和一致性。最后考虑的是自写业务逻辑，但这样做麻烦，编程复杂，效率低下。

## 索引

### **索引的作用？和它的优点缺点是什么？**

数据库索引，是数据库管理系统中一个排序的数据结构，以协助快速查询、更新数据库表中数据。索引的实现通常使用B树及其变种B+树。
在数据之外，数据库系统还维护着满足特定查找算法的数据结构，这些数据结构以某种方式引用（指向）数据，这样就可以在这些数据结构上实现高级查找算法。这种数据结构，就是索引。
为表设置索引要付出代价的：一是增加了数据库的存储空间，二是在插入和修改数据时要花费较多的时间(因为索引也要随之变动)。

- 创建索引可以大大提高系统的性能（优点）：
  第一，通过创建唯一性索引，可以保证数据库表中每一行数据的唯一性。
  第二，可以大大加快数据的检索速度，这也是创建索引的最主要的原因。
  第三，可以加速表和表之间的连接，特别是在实现数据的参考完整性方面特别有意义。
  第四，在使用分组和排序子句进行数据检索时，同样可以显著减少查询中分组和排序的时间。
  第五，通过使用索引，可以在查询的过程中，使用优化隐藏器，提高系统的性能。
  也许会有人要问：增加索引有如此多的优点，为什么不对表中的每一个列创建一个索引呢？
- 增加索引也有许多不利的方面：
  第一，创建索引和维护索引要耗费时间，这种时间随着数据量的增加而增加。
  第二，索引需要占物理空间，除了数据表占数据空间之外，每一个索引还要占一定的物理空间，如果要建立聚簇索引，那么需要的空间就会更大。
  第三，当对表中的数据进行增加、删除和修改的时候，索引也要动态的维护，这样就降低了数据的维护速度。
  索引是建立在数据库表中的某些列的上面。在创建索引的时候，应该考虑在哪些列上可以创建索引，在哪些列上不能创建索引。
- 一般来说，应该在这些列上创建索引：
  （1）在经常需要搜索的列上，可以加快搜索的速度；
  （2）在作为主键的列上，强制该列的唯一性和组织表中数据的排列结构；
  （3）在经常用在连接的列上，这些列主要是一些外键，可以加快连接的速度；
  （4）在经常需要根据范围进行搜索的列上创建索引，因为索引已经排序，其指定的范围是连续的；
  （5）在经常需要排序的列上创建索引，因为索引已经排序，这样查询可以利用索引的排序，加快排序查询时间；
  （6）在经常使用在WHERE子句中的列上面创建索引，加快条件的判断速度。
- 同样，对于有些列不应该创建索引：
  第一，对于那些在查询中很少使用或者参考的列不应该创建索引。这是因为，既然这些列很少使用到，因此有索引或者无索引，并不能提高查询速度。相反，由于增加了索引，反而降低了系统的维护速度和增大了空间需求。
  第二，对于那些只有很少数据值的列也不应该增加索引。这是因为，由于这些列的取值很少，例如人事表的性别列，在查询的结果中，结果集的数据行占了表中数据行的很大比例，即需要在表中搜索的数据行的比例很大。增加索引，并不能明显加快检索速度。
  第三，对于那些定义为text, image和bit数据类型的列不应该增加索引。这是因为，这些列的数据量要么相当大，要么取值很少。
  第四，当修改性能远远大于检索性能时，不应该创建索引。这是因为，修改性能和检索性能是互相矛盾的。当增加索引时，会提高检索性能，但是会降低修改性能。当减少索引时，会提高修改性能，降低检索性能。因此，当修改性能远远大于检索性能时，不应该创建索引。

### 索引有哪些种类

- 普通索引: 即针对数据库表创建索引
- 唯一索引: 与普通索引类似，不同的就是：MySQL数据库索引列的值必须唯一，但允许有空值
- 主键索引: 它是一种特殊的唯一索引，不允许有空值。一般是在建表的时候同时创建主键索引
- 组合索引: 为了进一步榨取MySQL的效率，就要考虑建立组合索引。即将数据库表中的多个字段联合起来作为一个组合索引。

## Mysql基本特性

### **drop,delete与truncate的区别**

- drop直接删掉表 。
- truncate删除表中数据，再插入时自增长id又从1开始 。
- delete删除表中数据，可以加where字句。
- DELETE语句执行删除的过程是每次从表中删除一行，并且同时将该行的删除操作作为事务记录在日志中保存以便进行进行回滚操作。TRUNCATE TABLE 则一次性地从表中删除所有的数据并不把单独的删除操作记录记入日志保存，删除行是不能恢复的。并且在删除的过程中不会激活与表有关的删除触发器。执行速度快。
- 表和索引所占空间。当表被TRUNCATE 后，这个表和索引所占用的空间会恢复到初始大小，而DELETE操作不会减少表或索引所占用的空间。drop语句将表所占用的空间全释放掉。
- 一般而言，drop > truncate > delete
- 应用范围。TRUNCATE 只能对TABLE；DELETE可以是table和view
- TRUNCATE 和DELETE只删除数据，而DROP则删除整个表（结构和数据）。
- truncate与不带where的delete ：只删除数据，而不删除表的结构（定义）drop语句将删除表的结构被依赖的约束（constrain),触发器（trigger)索引（index);依赖于该表的存储过程/函数将被保留，但其状态会变为：invalid。
- delete语句为DML（data maintain Language),这个操作会被放到 rollback segment中,事务提交后才生效。如果有相应的 tigger,执行的时候将被触发。
- truncate、drop是DLL（data define language),操作立即生效，原数据不放到 rollback segment中，不能回滚。
- 在没有备份情况下，谨慎使用 drop 与 truncate。要删除部分数据行采用delete且注意结合where来约束影响范围。回滚段要足够大。要删除表用drop;若想保留表而将表中数据删除，如果于事务无关，用truncate即可实现。如果和事务有关，或老师想触发trigger,还是用delete。
- Truncate table 表名 速度快,而且效率高,因为:
  truncate table 在功能上与不带 WHERE 子句的 DELETE 语句相同：二者均删除表中的全部行。但 TRUNCATE TABLE 比 DELETE 速度快，且使用的系统和事务日志资源少。DELETE 语句每次删除一行，并在事务日志中为所删除的每行记录一项。TRUNCATE TABLE 通过释放存储表数据所用的数据页来删除数据，并且只在事务日志中记录页的释放。
- TRUNCATE TABLE 删除表中的所有行，但表结构及其列、约束、索引等保持不变。新行标识所用的计数值重置为该列的种子。如果想保留标识计数值，请改用 DELETE。如果要删除表定义及其数据，请使用 DROP TABLE 语句。
- 对于由 FOREIGN KEY 约束引用的表，不能使用 TRUNCATE TABLE，而应使用不带 WHERE 子句的 DELETE 语句。由于 TRUNCATE TABLE 不记录在日志中，所以它不能激活触发器。

### HAVNG子句 和 WHERE 的异同点?

- 语法上：where 用表中列名，having 用 select 结果别名
- 影响结果范围：where 从表读出数据的行数，having 返回客户端的行数
- 索引：where 可以使用索引，having 不能使用索引，只能在临时结果集操作
- where 后面不能使用聚集函数，having 是专门使用聚集函数的。

### **MySQL** **当记录不存在时** insert,当记录存在时 update，语句怎么写？

INSERT INTO table (a,b,c) VALUES (1,2,3) ON DUPLICATE KEY UPDATE c=c+1;

### 一张表，里面有 ID 自增主键，当 insert 了 17 条记录之后， 删除了第 15,16,17 条记录，再把 Mysql 重启，再insert 一条记录，这条记录的 ID 是 18 还是 15 ？

- 如果表类型是MyISAM,那么ID是18。因为MyISAM会把主键自增的最大ID记录到数据文件里面，重启Mysql不会丢失最大ID。
- 如果表是InnoDB，那么ID是15。InnoDB只是把主键的最大ID记录到内存中，重启或者对表记星OPTIMIZE操作的时候都会丢失最大ID。

### **CHAR 和 VARCHAR 的区别？**

- CHAR 和 VARCHAR 类型在存储和检索方面有所不同
- CHAR 列长度固定为创建表时声明的长度，长度值范围是 1 到 255 当 CHAR 值被存储时，它们被用空格填充到特定长度，检索 CHAR 值时需删除尾随空格。

### SQL 的 select 语句完整的执行顺序

1、from子句组装来自不同数据源的数据；
2、where子句基于指定的条件对记录行进行筛选；
3、group by子句将数据划分为多个分组；
4、使用聚集函数进行计算；
5、使用having子句筛选分组；
6、select 计算所有的表达式；
7、使用order by对结果集进行排序。

### SQL 之连接查询（左连接和右连接的区别）

**外连接：**
左连接（左外连接）：以左表作为基准进行查询，左表数据会全部显示出来，右表如果和左表匹配的数据则显示相应字段的数据，如果不匹配则显示为 null。
右连接（右外连接）：以右表作为基准进行查询，右表数据会全部显示出来，左表如果和右表匹配的数据则显示相应字段的数据，如果不匹配则显示为 null。
全连接：先以左表进行左外连接，再以右表进行右外连接
**内连接：**
显示表之间有连接匹配的所有行。

### **谈一谈对MySQL InnoDB的认识**

#### **介绍：**

InnoDB引擎是MySQL数据库的一个重要的存储引擎,和其他存储引擎相比,InnoDB引擎的优点是支持兼容ACID的事务(类似于PostgreSQL),以及参数完整性(有外键)等。现在Innobase实行双认证授权.MySQL5.5.5以后默认的存储引擎都是InnoDB引擎。

#### **特点是：**

1、具有较好的事务支持：支持4个事务隔离级别，支持多版本读
2、行级锁定：通过索引实现，全表扫描仍然会是表锁，注意间隙锁的影响
3、读写阻塞与事务隔离级别相关
4、具有非常高效的缓存特性：能缓存索引，也能缓存数据
5、整个表和主键以Cluster方式存储，组成一颗平衡树
6、所有Secondary Index都会保存主键信息

#### **适用场景：**

1、需要事务支持（具有较好的事务特性）
2、行级锁定对高并发有很好的适应能力，但需要确保查询是通过索引完成
3、数据更新较为频繁的场景
4、数据一致性要求较高
5、硬件设备内存较大，可以利用InnoDB较好的缓存能力来提高内存利用率，尽可能减少磁盘IO

### myisam与 innodb 的区别？

1. **事务支持：** MyISAM：强调的是性能，每次查询具有原子性**,**其执行数度比InnoDB类型更快，但是不提供事务支持。InnoDB：提供事务支持事务，外部键等高级数据库功能。 具有事务(commit)、回滚(rollback)和崩溃修复能力(crash recovery capabilities)的事务安全(transaction-safe (ACID compliant))型表。
2. InnoDB 支持行级锁，而 MyISAM 支持表级锁. 用户在操作myisam 表时，select，update，delete，insert 语句都会给表自动加锁，如果加锁以后的表满足 insert 并发的情况下，可以在表的尾部插入新的数据。
3. InnoDB 支持 MVCC, 而 MyISAM 不支持
4. InnoDB 支持外键，而 MyISAM 不支持
5. **表主键 ：** MyISAM：允许没有任何索引和主键的表存在，索引都是保存行的地址。 InnoDB：如果没有设定主键或者非空唯一索引，就会自动生成一个 6 字节的主键(用户不可见)，数据是主索引的一部分，附加索引保存的是主索引的值。
6. InnoDB 不支持全文索引，而 MyISAM 支持。
7. **可移植性、备份及恢复 ：** MyISAM：数据是以文件的形式存储，所以在跨平台的数据转移中会很方便。在备份和恢复时可单独针对某个表进行操作。 InnoDB：免费的方案可以是拷贝数据文件、备份binlog，或者用 mysqldump，在数据量达到几十 G 的时候就相对痛苦了
8. **存储结构 ：** MyISAM：每个 *MyISAM* *在磁盘上存储成三个文件。第一*个文件的名字以表的名字开始，扩展名指出文件类型。.frm文件存储表定义。数据文件的扩展名为.MYD (MYData)。索引文件的扩展名是.MYI (MYIndex)。 InnoDB：所有的表都保存在同一个数据文件中（也可能是多个文件，或者是独立的表空间文件），InnoDB 表的大小只受限于操作系统文件的大小，一般为 2GB。

### 参考资料

1. https://www.cnblogs.com/yangk1996/p/12864004.html
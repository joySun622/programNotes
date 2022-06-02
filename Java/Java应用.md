[Toc]

# 连接Mysql数据库报错

## ClassCastException

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

## The server time zone value 'EDT' is unrecognized or represents

- **场景描述**

> 连接数据库报错,报错信息如下：

````
java.sql.SQLException: The server time zone value 'EDT' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:127)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:95)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:87)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:61)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:71)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:76)
	at com.mysql.cj.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:862)
````

- **原因**

> 数据库时间&系统服务时间不一致导致

- **解决方案**

> （1）使用 server mysql start命令启动mysql
>
> （2）在mysql中执行show variables like '%time_zone%';
>
> ![img](images/20170313182717565)
>
> （3）输入select now();
>
> ![img](images/20170313182853395)
>
> （4）在终端执行date命令
>
> ![img](images/20170313183006319)
>
> 此时发现终端显示的时间和MySql中显示的时间不一致，这就是问题所在。
>
> （5）在mysql中执行 set time_zone=SYSTEM;
>
> 
>
> （6）再次在mysql中执行select now();
>
> ![img](images/20170313183143539)
>
> （6）执行 set global time_zone='+8:00';
>
> （7）执行 flush privileges;
>
> （7）再次执行hive命令，问题解决。

# MongoDb

## MongoSocketException

### Mongo异常列表

> - `MongoQueryException`
> - `MongoSecurityException`
> - `MongoServerException`
> - `MongoSocketClosedException`
> - `MongoSocketException`
> - `MongoSocketOpenException`
> - `MongoSocketReadException`
> - `MongoSocketReadTimeoutException`
> - `MongoSocketWriteException`
> - `MongoTimeoutException`
> - `MongoWaitQueueFullException`
> - `MongoWriteConcernException`
> - `MongoWriteException`

- **场景描述**

> 当使用`mongodb`的**一主一从一备**节点构建的集群，使用java代码连接集群时候，测试主节点master和备用节点slave切换的时候
>
> **报错信息**：

```
Got socket exception on connection [connectionId{localValue:4, serverValue:15}] to 172.19.32.142:27017. All connections to 172.19.32.142:27017 will be closed

2019-10-23 20:21:05,972 WARN [org.mongodb.driver.connection] - Got socket exception on connection [connectionId{localValue:4, serverValue:15}] to 172.19.32.142:27017. All connections to 172.19.32.142:27017 will be closed.
com.mongodb.MongoSocketReadException: Prematurely reached end of stream
	at com.mongodb.connection.SocketStream.read(SocketStream.java:88)
	at com.mongodb.connection.InternalStreamConnection.receiveResponseBuffers(InternalStreamConnection.java:494)
	at com.mongodb.connection.InternalStreamConnection.receiveMessage(InternalStreamConnection.java:224)
```

- **解决方案1**

> `catch`异常，然后重新创建连接

- **范例**

```
package mongotest;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: java连接mongdb集群；测试高可用性
 * @author: fangchangtan
 * @version 创建时间：2018年11月26日 下午7:45:29
 */
public class TestMongoDBReplSet1 {

    public static void main(String[] args) {
        ArrayList<ServerAddress> arrayList = new ArrayList<>();
            //mongpdb的java连接配置为一个集群模式
            List<ServerAddress> addresses = new ArrayList<ServerAddress>();
            ServerAddress address1 = new ServerAddress("172.19.32.141" , 27017);
            ServerAddress address2 = new ServerAddress("172.19.32.141" , 27027);
            ServerAddress address3 = new ServerAddress("172.19.32.141" , 27037);
            addresses.add(address1);
            addresses.add(address2);
            addresses.add(address3);

            // 3.用来做mongo复制集的基本配置
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
//            //线程等待连接变为可用的最长时间.
//            builder.maxWaitTime(20000);
//            //连接超时时间,必须大于0
//            builder.connectTimeout(1000 * 5);
//            //设置服务器选择超时（以毫秒为单位），它定义驱动程序在抛出异常之前等待服务器选择成功的时间
//            //值为0表示如果没有可用的服务器，它将立即超时。 负值意味着无限期等待
//            builder.serverSelectionTimeout(1000 * 30);
//            // 4.自动重连
//            // 7.每个连接上的线程数
//            builder.threadsAllowedToBlockForConnectionMultiplier(50);
//            builder.maxConnectionIdleTime(5000);//set the max wait time in (ms)

            MongoClient mongoClient = new MongoClient(addresses,builder.build());
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("testdb");

            for (int i = 0; i < 1000; i++) {
                System.out.println("==========================");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //向集群中插入文档数据
//                collection.insertOne(new Document("name","dog"+i));
                try {
                    collection.deleteMany(new BasicDBObject("_id", new BasicDBObject("$ne", null)));
                    collection.insertOne(new Document("name","dog"+i));
                }catch (MongoSocketException e){
                    System.out.println("++++++++++++++++++++++");
                    System.out.println(e);
                    mongoClient = new MongoClient(addresses,builder.build());
                }

                //查询集群中的数据记录
                FindIterable<Document> find = collection.find();
                MongoCursor<Document> iterator = find.iterator();
                while (iterator.hasNext()) {
                    final Document document = iterator.next();
                    System.out.println(document);

                }
            }
    }
}

```

# 参考来源

1. https://blog.csdn.net/u014662563/article/details/61923884
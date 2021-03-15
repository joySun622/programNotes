[toc]

### CAP理论？

> - C : Consistency 一致性,数据在多个副本之间似否能够保持一致的特性。
> - A: Availability 可用性，系统服务必须一直处于可用状态，对每个请求总是在指定的时间返回结果。
> - P:Partition tolerance 分区容错性,遇到分区网络故障时，仍能对外提供一致性和可用性的服务。

### BASE理论？

> Basically Available(基本可用)、Soft state(软状态) 和 Eventuanlly consistent （最终一致性）3个短语的简写。
>
> - 基本可用：系统出现不可预知的故障时，允许损失部分可用性。
> - 弱（软）状态：数据的中间状态，并认为改状态存在不会一项系统整体可用性，允许不同节点数据副本数据同步过程中的延时。
> - 最终一致性：系统中所有数据副本，在一段时间的同步后，最终数据能够到一致性的状态。

### 什么是zookeeper？

> ```
> 1、zookeeper是一个分布式协调技术，是分布式数据一致性解决方案的典型代表，力求做到强一致性但最终实现的是最终一致性，采用CAP理论的AP，用来构建高可用分布式主备系统
> ```

### zookeeper能干什么？

> ```
> 1、数据发布/订阅
> 2、命名服务
> 3、集群管理
> 4、Master选举
> 5、分布式锁
> 6、分布式队列
> 7、负载均衡
> ```

### zookeeper提供了什么？

> ```
> 1、文件系统
> 2、消息广播
> ```

### 简单说一下zookeeper的文件系统？

> ```
> 1、zookeeper集群是有一个叫做命名节点空间的概念，其中节点就是znode,在zookeeper的文件系统中有两种节点，一是数据节点，二是目录节点，但是只有数据节点能存放数据
> 2、zookeeper集群为了维护高吞吐和低延迟的特性，就维护了这样树状的目录结构，而且数据节点的存储量不能太大，最多为1M
> ```

### 说一下ZAB协议？

> ```
> 1、ZAB协议是为分布式协调技术zookeeper提供的一个支持崩溃回复的原子广播协议
> 2、ZAB包括两种模式即崩溃回复和消息广播
> 3、首先当服务刚启动的时候或者leader节点宕机或者集群内有一半以上机器不能正常工作，此时会进入leader选举状态，然后再向其它服务器同步数据（采用的是两阶段提交），只要有一般以上返回成功的结果，此时便进入消息广播模式，整个zookeeper集群就可以对外提供服务并处理客户端的请求
> ```

### zookeeper有几种数据节点？分别是什么？

> ```
> 1、7种
> 	持久节点				除非手动删除，否则数据一直存在
> 	临时节点				临时节点的生命周期与客户端会话绑定，当会话关闭，数据才会丢失
> 	持久顺序节点			和持久节点类似，只是多了顺序性，该特性是由父节点维护的一个自增整型数字
> 	临时顺序节点			和临时节点类似，只是多了顺序性，该特性是由父节点维护的一个自增整型数字
> 	容器节点				当节点的最后一个子节点删除之后，该容器节点会被自动删除
> 	TTL节点				在节点上加了过期时间
> 	TTL顺序节点		        和TTL节点类似，只是多了顺序性，该特性是由父节点维护的一个自增整型数字	
> ```

### 简单说一下zookeeper Watcher机制（zookeeper数据变更通知）？

> ```
> 1、客户端注册Watcher
> 2、服务端触发Watcher
> 3、客户端回调Watcher
> 	首先客户端会向服务端注册一个Watcher监听，当服务端的某些指令触发了这个Watcher的话，服务端就会向客户端返回一个消息通知，客户端接收到消息之后便可以做出业务上的改变
> ```

### 客户端注册Watcher是如何实现的？

> ```
> 1、首先会访问getData()/getChildren()/exist()三个API，传入Watcher对象
> 2、标记请求request,将Watcher封装成WatcherRegistration对象
> 3、再封装成Packet对象，服务端发送request
> 4、接收到服务端的request请求之后，将Watcher注册到zkWatcherManager中进行管理
> 5、将结果返回给客户端，注册成功
> ```

### 服务端处理Watcher是怎样实现的？

> ```
> 1、首先根据客户端的请求判断是否需要注册Watcher
> 2、比如服务端触发了setData()/delete()/create()方法，会触发一个叫做NodeDataChanged（）的事件
> 3、服务端会将触发的事件类型、节点的路径封装成一个叫做WatchedEvent的对象
> 4、再向zkWatcherManager中的WatcherTable中根据节点路径查找
> 	若找不到，说明客户端没有注册过Watcher
> 	若找到，将Watcher从WatcherTable中删除（说明Watcher监听是一次性的）
> 5、调用process方法触发Watcher,主要是通过Servercnxn的TCP连接来通知客户端
> ```

### 客户端回调Watcher是怎样实现的？

> ```
> 1、客户端有个SendThread来接收Watcher通知的线程，接收的通知之后会交由EventThread线程处理
> 2、客户端的Watcher监听也是一次性的，一旦触发也会被删除
> ```

### 说一下你对zookeeper权限控制列表的认识（ACL）？

> ```
> 1、zookeeper的权限控制列表包含三个部分
> 	a、授权模式
> 		· IP:授权模式精确到IP粒度
> 		· Digest:相当于username:password，最常用的一种授权模式
> 		· World：相当于最特殊的Digest模式world:everyone,最开放的一种授权模式
> 		· Super：超级用户
> 	b、授权对象：被授予权限的用户例如某个IP
> 	c、权限
>		· Create:创建
> 		· Delete：删除
>		· Read：读
> 		· Write：写
> 		· Admin：管理
> ```

### 你知道Chroot属性是用来干什么的吗？

> ```
> 1、zookeeper新版本提出的一种特性，类似与namespace的说法，就是每个客户端与zookeeper的服务端之间的交互都保存在自己的namespace下
> 2、在目前的分布式系统中，很多都是多个应用共用一个zookeeper集群，如果设置了chroot属性，那么不同应用之间就可以起到很好的隔离效果
> ```

### zookeeper的会话管理是怎样的？

> ```
> 1、分桶策略：即将类似的会话分到同一个区内进行管理，这样做可以起到不同区块的分批处理以及同一区块的统一处理
> 2、分桶原则：下次会话超时时间点
> 3、计算公式：{[(当前时间+session超时时间）/（tickTime）]+1}*tickTime (其中tickTime是会话超时检查时间间隔)
> ```

### zookeeper中的服务器角色都有哪些？分别是干什么的？

> ```
> 1、leader
> 	a、事务的执行和调度者，保证事务的顺序性
> 	b、集群内部各服务的调度者
> 2、follower
> 	a、处理非事务请求，将事务请求转发给leader处理
> 	b、参与集群内Proposal(提议)的投票
> 	c、参与leader选举的投票
> 3、observer
>	a、处理非事务请求，将事务请求转发给leader
> 	b、在不影响事务处理能力的基础上增加了处理非事务请求的能力
>	c、不参与任何形式的投票
> ```

### zookeeper中Server的工作状态？

> ```
> 1、looking:集群中leader选举的状态
> 2、leading:说明当前服务器角色是leader节点
> 3、following:说明当前服务器角色是follower节点
> 4、observing:说明当前服务器角色是observer节点
> ```

### zookeeper数据同步的实现方式？

> ```repl
> 1、zookeeper实现数据同步有4种方式，即直接差异化同步、先回滚再差异化同步、仅回滚同步、全量同步
> 2、首先先从learner(follower和observer节点的统称)节点获取最后一次操作数据的zxid,再从leader节点中获取最小的minZxid和最大的maxZxid
> 3、a、如果minZxid<zxid<maxZxid	 -->采用直接差异化同步
> b、如果leader服务器发现learner服务器包含了自己不存在的数据，那么learner服务器需要回滚到leader包含的数据然后在差异化处理 
>                               -->先回滚再差异化同步
> c、如果zxid>maxZxid            -->仅回滚同步即可
> d、如果zxid<minZxid            -->全量同步
> ```

### zookeeper是如何保证事务的顺序一致性的？

> ```
> 1、zookeeper采用全局递增的事务id(zxid)来保证事务的顺序一致性的
> 2、所有的提议在被提出的都会携带这个zxid,zxid是一个64位的数字；高32位是个epoch（时期，纪元，世，新时代）值，表示leader选举周期，每进行一次leader选举，该数字就会+1；后32位用来表示递增计数,当产生新的提议时，会依据数据库的两阶段提交过程，首先会像其它节点发送执行请求，如果超过半数以上的机器正确执行，那么该事务就可以被执行
> ```

### 分布式集群中为什么会有Master节点？

> ```
> 1、因为在分布式环境中，某些场景下你只需要计算一次，所有的节点都可以共享这个结果，避免重复计算，减少性能开销，所以就有了Master节点
> ```

### zookeeper节点宕机了怎么办？

> ```
> 1、因为zookeeper本身也是一个集群，并且推荐配置集群机器不少于3台
> 2、如果低于三台，挂了一台（无论是leader还是follower节点），集群都不能正常工作
> 3、如果集群机器>=3台，如果挂的是follower节点，不影响集群正常工作，如果挂的是leader节点，需要重新选举leader节点，选举完成之后还能正常工作
> ```

### zookeeper负载均衡和nginx负载均衡的区别？

> ```
> 1、zookeeper的负载均衡可以调控，nginx的负载均衡只能调权重，其它可调控的方面都需要自己手写
> 2、但是nginx的吞吐量远大于zookeeper,所以要根据不同的场景选择合适的负载均衡方案
> ```

### zookeeper有哪几种部署模式？

> ```
> 1、单机
> 2、伪集群
> 3、集群
> ```

### zookeeper集群至少需要几台机器？集群规则是怎样的？

> ```
> 1、至少需要3态机器
> 2、规则是：2n+1（n>0的整数）
> ```

### zookeeper集群支持动态添加机器吗？

> ```
> 1、动态添加机器说白了就是水平扩容，动态添加机器有以下两种方案
> 2、全部重启：先把集群中所有的服务都关掉，修改完配置之后再全部重启，不影响之前客户端的会话
> 3、逐个重启：因为zookeeper集群只要有半数以上的机器存活时，集群就能对外提供服务，因此我们可以逐个关闭机器，然后修改配置再重启
> ```

### zookeeper集群的Watcher监听是永久的吗？为什么？

> ```
> 1、不是永久的（一次性的）
> 2、因为一旦是永久的话，服务端的每次更改数据都会发送一个消息通知给客户端，这对于网络开销和服务器端的压力都是非常大的，而且我们一般都是需要最新的一次数据，不需要每次变更数据的结果，所以zookeeper在Watcher监听这一块设置一次性的，一旦触发了Watcher，监听就会被移除，下次想触发还需要重新注册
> ```

### zookeeper的java客户端有哪些？

> ```
> 1、zookeeper自带的zkClient
> 2、apache开源的Curator
> ```

### chubby是什么？与zookeeper比你怎么看？

> ```
> 1、chubby是google的一个完全采用Paxos算法的项目，不开源
> 2、zookeeper可以说是chubby开源的版本，采用ZAB协议，开源
> ```

### 说几个zookeeper常用的命令？

> ```
> 1、set /liulong 123
> 2、get /liulong
> 3、ls /
> 4、update /liulong 456
> 5、delete /liulong
> 6、stat /liulong
> ```

### ZAB协议和Paxos算法的联系（相同点和不同点）？

> ```
> 1、相同点：
> 	a、都有一个leader进程，并且该leader进程负责调控lollower进程的运行
> 	b、leader节点都需要经过半数以上的follower节点作出正确的反馈后，才会将一个提议提交
> 	c、在ZAB中每个提议都有一个epoll值，在Paxos中称为Ballot
> 2、不同点：
> 	a、ZAB采用AP,用来构建高可用分布式主备系统；Paxos采用CP，用来构建分布式一致性状态机系统
> ```

### Zookeeper的典型应用场景有哪些？

> ```repl
> 1、消息发布与订阅    -->发布者发送消息供订阅者消费消息
> 2、命令服务         -->采用全局路径，每个路径对应一个名称用来指定相应的服务
> 3、master选举
> 4、集群管理
> 5、负载均衡
> 6、分布式锁         -->两种（保持独占/控制时序）
> 	保持独占：每个znode都可以看作一把锁，在createZnode的时候，每个客户端都去创建/distribute_lock,最终创建成功的那个客户端就拥有了这把锁，用完之后删除/distribute_lock即可
> 	控制时序：在/distribute_lock下面创建临时顺序节点，编号最小的拥有这把锁，用完删除
>7、分布式队列：     -->两种（同步队列/FIFO队列）
> 	同步队列：当一个队列的所有成员都聚集时，这个队列才可用；只需要在约定目录下创建一个Watcher监听，当达到指定数量时，就触发Watcher通知机制
>	FIFO队列：与分布式锁差不多，入队时有编号，出队时按编号依次出队
> ```



### 参考资料

1. https://cloud.tencent.com/developer/article/1649436
2. https://www.cnblogs.com/liulong99/p/13036794.html
3. https://www.cnblogs.com/lanqiu5ge/p/9405601.html
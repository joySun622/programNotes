[toc]

### Dubbo是什么？

>  Dubbo是阿里巴巴开源的基于 Java 的高性能 RPC 分布式服务框架，现已成为 Apache 基金会孵化项目。

### 为什么要用Dubbo？

> 因为是阿里开源项目，国内很多互联网公司都在用，已经经过很多线上考验。内部使用了 Netty、Zookeeper，保证了高性能高可用性。
>
> 使用 Dubbo 可以将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，可用于提高业务复用灵活扩展，使前端应用能更快速的响应多变的市场需求。

### Dubbo需要 Web 容器吗？

> 不需要，如果硬要用 Web 容器，只会增加复杂性，也浪费资源。

### Dubbo内置了哪几种服务容器？

> - Spring Container
> - Jetty Container
> - Log4j Container

### Dubbo里面有哪几种节点角色？

> ![img](images/20181002113745869)

### 画一画服务注册与发现的流程图

![img](images/20181002113850939)

### Dubbo默认使用什么注册中心，还有别的选择吗？

> 推荐使用 Zookeeper 作为注册中心，还有 Redis、Multicast、Simple 注册中心，但不推荐。

### Dubbo有哪几种配置方式？

> 1）Spring 配置方式
> 2）Java API 配置方式

### Dubbo 核心的配置有哪些？

> ![img](images/20181002113904858)

### 在 Provider 上可以配置的 Consumer 端的属性有哪些？

> 1）timeout：方法调用超时
> 2）retries：失败重试次数，默认重试 2 次
> 3）loadbalance：负载均衡算法，默认随机
> 4）actives 消费者端，最大并发调用限制

### Dubbo启动时如果依赖的服务不可用会怎样？

> Dubbo 缺省会在启动时检查依赖的服务是否可用，不可用时会抛出异常，阻止 Spring 初始化完成，默认 check="true"，可以通过 check="false" 关闭检查。

### Dubbo推荐使用什么序列化框架，你知道的还有哪些？

> 推荐使用Hessian序列化，还有Duddo、FastJson、Java自带序列化。

### Dubbo默认使用的是什么通信框架，还有别的选择吗？

> Dubbo 默认使用 Netty 框架，也是推荐的选择，另外内容还集成有Mina、Grizzly。

### Dubbo有哪几种集群容错方案，默认是哪种？

> ![img](images/20181002113930188)

### Dubbo有哪几种负载均衡策略，默认是哪种？

> ![img](images/20181002113941952)

### 在使用过程中都遇到了些什么问题？

>  Dubbo 的设计目的是为了满足高并发小数据量的 rpc 调用，在大数据量下的性能表现并不好，建议使用 rmi 或 http 协议。

### 你还了解别的分布式框架吗？

> 别的还有 Spring cloud、Facebook 的 Thrift、Twitter 的 Finagle 等。

### Dubbo 和 Dubbox 有什么区别？

>  Dubbox 是继 Dubbo 停止维护后，当当网基于 Dubbo 做的一个扩展项目，如加了服务可 Restful 调用，更新了开源组件等。

### Dubbo 停止维护了吗？

> 2014 年开始停止维护过几年，17 年开始重新维护，并进入了 Apache 项目。

### 说说 Dubbo 服务暴露的过程。

> Dubbo 会在 Spring 实例化完 bean 之后，在刷新容器最后一步发布 ContextRefreshEvent 事件的时候，通知实现了 ApplicationListener 的 ServiceBean 类进行回调 onApplicationEvent 事件方法，Dubbo 会在这个方法中调用 ServiceBean 父类 ServiceConfig 的 export 方法，而该方法真正实现了服务的（异步或者非异步）发布。

### Dubbo的管理控制台能做什么？

> 管理控制台主要包含：路由规则，动态配置，服务降级，访问控制，权重调整，负载均衡，等管理功能。

### 如何解决服务调用链过长的问题？

> Dubbo 可以使用 Pinpoint 和 Apache Skywalking(Incubator) 实现分布式服务追踪，当然还有其他很多方案。

### Dubbo可以对结果进行缓存吗？

> 可以，Dubbo 提供了声明式缓存，用于加速热门数据的访问速度，以减少用户加缓存的工作量。

### Dubbo支持服务多协议吗？

> Dubbo 允许配置多协议，在不同服务上支持不同协议或者同一服务上同时支持多种协议。



### 参考资料

1. https://blog.csdn.net/moakun/article/details/82919804
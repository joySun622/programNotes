[toc]

**1、什么是springboot** 
用来简化spring应用的初始搭建以及开发过程 使用特定的方式来进行配置（properties或yml文件） 
创建独立的spring引用程序 main方法运行 
嵌入的Tomcat 无需部署war文件 
简化maven配置 
自动配置spring添加对应功能starter自动化配置 
答：spring boot来简化spring应用开发，约定大于配置，去繁从简，just run就能创建一个独立的，产品级别的应用

**2、****Spring Boot有哪些优点？** 
答：-快速创建独立运行的spring项目与主流框架集成 
-使用嵌入式的servlet容器，应用无需打包成war包 
-starters自动依赖与版本控制 
-大量的自动配置，简化开发，也可修改默认值 
-准生产环境的运行应用监控 
-与云计算的天然集成

**3、如何重新加载Spring Boot上的更改，而无需重新启动服务器？** 
这可以使用DEV工具来实现。通过这种依赖关系，您可以节省任何更改，嵌入式tomcat将重新启动。 
Spring Boot有一个开发工具（DevTools）模块，它有助于提高开发人员的生产力。Java开发人员面临的一个主要挑战是将文件更改自动部署到服务器并自动重启服务器。 
开发人员可以重新加载Spring Boot上的更改，而无需重新启动服务器。这将消除每次手动部署更改的需要。Spring Boot在发布它的第一个版本时没有这个功能。 
这是开发人员最需要的功能。DevTools模块完全满足开发人员的需求。该模块将在生产环境中被禁用。它还提供H2数据库控制台以更好地测试应用程序。 

org.springframework.boot 
spring-boot-devtools 
true 
**4、Spring Boot中的监视器是什么？** 
Spring boot actuator是spring启动框架中的重要功能之一。Spring boot监视器可帮助您访问生产环境中正在运行的应用程序的当前状态。 
有几个指标必须在生产环境中进行检查和监控。即使一些外部应用程序可能正在使用这些服务来向相关人员触发警报消息。监视器模块公开了一组可直接作为HTTP URL访问的REST端点来检查状态。

**5、什么是YAML？** 
YAML是一种人类可读的数据序列化语言。它通常用于配置文件。 
与属性文件相比，如果我们想要在配置文件中添加复杂的属性，YAML文件就更加结构化，而且更少混淆。可以看出YAML具有分层配置数据。

**6、springboot自动配置的原理** 
在spring程序main方法中 添加@SpringBootApplication或者@EnableAutoConfiguration 
会自动去maven中读取每个starter中的spring.factories文件 该文件里配置了所有需要被创建spring容器中的bean

**7、springboot读取配置文件的方式** 
springboot默认读取配置文件为application.properties或者是application.yml

**8、springboot集成mybatis的过程** 
添加mybatis的starter maven依赖 

org.mybatis.spring.boot 
mybatis-spring-boot-starter 
1.2.0 

在mybatis的接口中 添加@Mapper注解 
在application.yml配置数据源信息

**9、Spring Boot 的核心注解是哪个？它主要由哪几个注解组成的？**

启动类上面的注解是@SpringBootApplication，它也是 Spring Boot 的核心注解，主要组合包含了以下 3 个注解：

@SpringBootConfiguration：组合了 @Configuration 注解，实现配置文件的功能。

@EnableAutoConfiguration：打开自动配置的功能，也可以关闭某个自动配置的选项，如关闭数据源自动配置功能： @SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })。

@ComponentScan：Spring组件扫描。

**10、开启 Spring Boot 特性有哪几种方式？**

1）继承spring-boot-starter-parent项目

2）导入spring-boot-dependencies项目依赖

**11、Spring Boot 需要独立的容器运行吗？**

可以不需要，内置了 Tomcat/ Jetty 等容器。

**12、运行 Spring Boot 有哪几种方式？**

1）打包用命令或者放到容器中运行

2）用 Maven/ Gradle 插件运行

3）直接执行 main 方法运行

**13、你如何理解 Spring Boot 中的 Starters？**

Starters可以理解为启动器，它包含了一系列可以集成到应用里面的依赖包，你可以一站式集成 Spring 及其他技术，而不需要到处找示例代码和依赖包。如你想使用 Spring JPA 访问数据库，只要加入 spring-boot-starter-data-jpa 启动器依赖就能使用了。

**14、Spring Boot 支持哪些日志框架？推荐和默认的日志框架是哪个？**

Spring Boot 支持 Java Util Logging, Log4j2, Lockback 作为日志框架，如果你使用 Starters 启动器，Spring Boot 将使用 Logback 作为默认日志框架.

**15、SpringBoot 实现热部署有哪几种方式？**

主要有两种方式：

- Spring Loaded
- Spring-boot-devtools

### 参考资料

1. https://www.cnblogs.com/lingboweifu/p/11797926.html
[Toc]

# Debug失效

- **问题详情**

  > 使用eclipse以debug方式启动一个web项目，发现项目中的断点都直接跳过去了

- **解决方案**

  1. 查看是否选择`skip all breakpoints`
     ![image-20220209165717416](images/image-20220209165717416.png)

     <img src="images/6ca98461862541936cf1f9b0df1bd10ff326abad.jpg" alt="eclipse中打断点debug无效是什么原因？" style="zoom:67%;" />

  2. 勾选`Skip breakpoints during a 'Run to Line' operation  `

<img src="images/image-20220209165139046.png" alt="image-20220209165139046" style="zoom:67%;" />

# 启动Tomcat报错

- **环境&问题说明**

```
环境说明：
eclipse版本  2019-09 R (4.13.0)
JDK:1.6
Tomcat: 6.0

问题说明：配置好项目对应的jdk和使用的Tomcat后，启动Tomcat Servelet，一启动便报错，报错信息部分如下：

2022-2-7 15:48:18 org.apache.catalina.core.StandardContext listenerStart
严重: Error configuring application listener of class hd.core.framework.spring.listener.SpringAppListener
java.lang.Error: Unresolved compilation problems: 
	The import javax.servlet.ServletContextEvent cannot be resolved
	The import javax.servlet.ServletContextListener cannot be resolved
	ServletContextListener cannot be resolved to a type
	ServletContextEvent cannot be resolved to a type
	ServletContextEvent cannot be resolved to a type
```

- **原因**

> 没有导入servlet-api.jar，缺少servlet-api.jar包导致编译错误。一般来说，创建一个servlet,通常是需要servlet.api-jar和jsp-api.jar的支持

- **解决方案**：Build Path添加servlet-api.jar或者添加Server Runtime
  - **方案1**：添加Server Runtime

<img src="images/image-20220207163351753.png" alt="image-20220207163351753" style="zoom:67%;" />

- - **方案2**：添加servlet-api.jar包

<img src="images/20201027105242932.png" alt="在这里插入图片描述" style="zoom:67%;" />

<img src="images/image-20220207163216082.png" alt="image-20220207163216082" style="zoom: 67%;" />

# 参考资料

1. https://www.itdaan.com/blog/2017/09/11/42289157e165c9c5e69f262c27f4fdae.html
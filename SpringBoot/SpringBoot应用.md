[Toc]

# 使用外部TOMCAT

- **场景描述**

> 开发工具：IDE
> JDK:1.8
>
> TOMCAT:8.5.47
> SpringBoot:2.7.0
>
> 使用IDE创建SpringBoot项目后，启动，正常。但使用外部Tomcat8.5版本启动项目时，发现项目无法正常被加载到Tomca服务

- **解决方案**

> SpringBoot 2.x版本内置的Tomcat为Tomcat9.x，当使用Tomcat 8.x版本作为启动服务会导致项目无法正常加载。



# 参考资料

1. https://www.cnblogs.com/wu-kai/p/13368977.html
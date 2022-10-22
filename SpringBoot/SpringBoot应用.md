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



# 配置SpringBoot启动

![image-20220906174025498](images/image-20220906174025498.png)

![image-20220906174120390](images/image-20220906174120390.png)

# `@DateTimeFormat` & `@JsonFormat`

## `@DateTimeFormat`

> 该注解主要解决前端时间控件传值到后台接收准确的Date类属性的问题，我们可以在需要接收的类中对应的时间类型属性上加上@DateTimeFormat注解，并在注解中加上pattern属性，例如：
>
> ```
> public class Person{
> 
> 	// 名字
> 	private String name;
> 	
> 	// 出生年月日
> 	@DateTimeFormat(pattern = "yyyy-MM-dd")
> 	private Date birthday;
> }
> ```
>
> 随后在前端对应birthday传入的`yyyy-MM-dd`日期类型就能够按照该种格式进行转义赋值给Person类中的birthday的Date类型。
> 若想按照yyyy/MM/dd的日期格式，则直接修改@DateTimeFormat的pattern属性为对应的日期格式即可，即`@DateTimeFormat(pattern = “yyyy/MM/dd”)`。或者还想要接收到对应的HH-mm-ss时分秒，同样在pattern中加上即可，如`@DateTimeFormat(pattern = “yyyy-MM-dd HH-mm-ss”)`

## `@JsonFormat`

> 该注解主要解决后台从数据库中取出时间类型赋予java对象的Date属性值无法在前端以一定的日期格式来呈现，默认返回的是一个带时区的格式串，不符合我们日常要呈现的yyyy-MM-dd格式的日期。
> 同样，我们在对应的接收对象时间类型上加上@JsonFormat注解，并在注解中加上pattern属性以及timezone属性，例如：
>
> ```
> public class Person{
> 
> 	// 名字
> 	private String name;
> 	
> 	// 出生年月日
> 	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
> 	@DateTimeFormat(pattern = "yyyy-MM-dd")
> 	private Date birthday;
> }
> ```
>
> 表示我们从后台数据库查询结果赋予对应的Date java类型为yyyy-MM-dd格式，且注意后面的timezone属性，最好加上GMT+8表示我们以东八区时区为准，若读者想用其他时区就可以通过更改timezone属性来改变，从而解决时区不同造成的时间差问题。

# 参考资料

1. https://www.cnblogs.com/wu-kai/p/13368977.html
2. https://blog.csdn.net/imVainiycos/article/details/102712056
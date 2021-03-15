[toc]

**Struts2 试题**
**1.struts2的执行流程？**

客户端提交一个HttpServletRequest请求（action或JSP页面）
请求被提交到一系列Filter过滤器，如ActionCleanUp和FiterDispatcher等
FilterDispatcher是Struts2控制器的核心，它通常是过滤器链中的最后一个过滤器
询问ActionMapper是否需要调用某个action来处理
如果ActonMapper据诶的那个需要调用某个A传统，
FilterDispatcher则把请求教到ActionProxy，
由其进行处理 ActionProxy
通过Configuration Manager询问框架配置文件，找到需要调用的Action类
ActionProxy创建一个ActionInvocation实例，而ActionInvocation通过代理模式调用
action Action执行完毕后，返回一个result字符串，
此时再按相反的顺序通过Interceptor拦截器
最后ActionInvocation负责根据struts配置文件中配置的result元素，
找到与返回值对应的result

**2.struts2的web里需要配置什么？**

```java
 <filter>   
 <!-- 配置Struts2核心Filter的名字 -->  
 <filter-name>struts2</filter-name>  
 <!-- 配置Struts2核心Filter的实现类 -->   
 <filter-class>org.apache.struts2.dispatcher.FilterDispatcher</f ilter-class>  
 </filter>  
 <!-- 配置Filter拦截的URL -->  
 <filter-mapping>   
 <!-- 配置Struts2的核心FilterDispatcher拦截所有用户请求 --> 
 <filter-name>struts2</filter-name>  
 <url-pattern>/*</url-pattern>  
 </filter-mapping>
123456789101112
```

**3.struts.xml文件哪个常量可以实现不用重新启动服务就可以得到反馈？**

 Timmer

**4.struts中的package的作用是什么？namespace可以为空吗？如果namespace为空会意味着什么呢？**
struts中的package的作用是标识
namespace可以为空
如果namespace为空会意味着:
如果没写，说明该包为默认空间
只要敲index没有精确对应的package都交给namespackage 如果还没有就崩溃

**5.Action 的实现通集成常用那种方法？**
一种是直接实现自己的Action
另一种是实现Action的接口
继承ActionSupport

**6.路径设置用什么路径，绝对还是相对？**
都可以

**7.通配符有几种？要遵循什么原则？**
｛1｝ 方法名

**8.Action的属性？**
path属性：指定请求访问Action的路径
type属性：指定Action的完整类名
name属性：指定需要传递给Action的ActionForm Bean
scope属性：指定ActionForm Bean的存放范围
validate属性：指定是否执行表单验证
input属性：指定当表单验证失败时的转发路径。
`<action>`元素还包含一个`<forward>`子元素，它定义了一个请求转发路径。

**9.用于读取资源文件，实现国际化的Struts2的标签（ <s:i18n> ）。**

**10.一下关于struts的描述中，不正确的是（D）**
A、struts 是一个基于JAVA EE的MVC
B、struts2的框架配置文件是struts-configxml
C、struts2的控制组建核心包括核心控制和业务控制器
D、在struts中解决中文乱码可以通过修改struts。I18n。encoding的值为GBK来实现

**11.不属于Struts2的动作类的常量返回值的是（C）**
A．success B．input C．never D．Login

**12．一下不属于Struts2动作执行的结果类型是（A）**
a．action b．redirect c．redirectAction d．dispatcher

**13.在struts.xml中。假如在一个package中没有配置namespace，那么在其中配置一个Action名字叫做login，它的result是这样配置的**`<result>/login.jsp</result>,`已知在此配置文件中还有另一个name为user的package其中也有一个Action名字为login，它的result是这样配置
的/loginUser.jsp那么在以下什么情况下将能访问到login.jsp (A)
A.在根路径后输入/login
B.在根路径后输入/user/login
C.在根路径后输入/login/user
D.在根路径后输入/abc/login

**14.正规开发中，在使用struts2时定义Action使用哪种方式。 (B)**

A.直接定义Action类。 B.从ActionSupport继承。 C.从Action继承。 D.实现Action接口。

**15.不同的线程在调用Action时创建几个Action实例。©**
A.1个 B.2个 C.每个线程创建1个。 D.可以自定义配置。

**16.struts2的体系结构主要包括那几个部分（ A ）**
A.模型层 B.struts控制器组件 C。struts配置文件 D。struts标签库

**17.struts提供了Action接口定义了5个标准字符串常量，不包括的有（C ）**
A SUCCESS
B NONE
C REG
D LOGIN

**18.struts中数据验证失败时，通过（ B ）标签显示错误信息。**
A <s:text>
B <s:error>
C <s:faild>
D <s:fielderror>

**19.列举出action执行结果类型，并说明用途。**

默认结果类型是dispatcher
chain：Action链式处理的结果类型，也就是将结果转发到这个action中。
dispatcher：用于整合JSP的结果类型
redirect：实际上dispatcher和redirect的区别就是在于转发和重定向的区别。
redirect-action：重定向action

**20.简述struts2文件上传的方法。**

Commons FileUpload通过将HTTP的数据保存到临时文件夹，然后Struts使用fileUpload拦截器将文件绑定到Action的实例中。从而我们就能够以本地文件方式的操作浏览器上传的文件。

**21.在struts2标签内部 能否使用el表达式？**

不可

**22.struts2中result-type的chain和redirectAction的区别**
chain，它是一个琏式的，
是从一个Action转发另外一个Aciton redirectAction,
是从新请求一个Action

**23.<s:property/>能否取到Httpsession的值**
不可

**24.action的name里边写的名字是什么地方用的?**
From 表单action引用

**25.action的class里面写的是什么？**
Action相应类的详细路径

**26.action的extends里面调用的是什么？**
从另外一个包继承、相当于拥有即承包的所有配置

**27.如果不写action的后面mothod，默认的调用的方法是什么，如果写的话，写的时候有什么需要注意的？**
不写会默认调用最上面的方法

**28.输出值的strtus标签是什么？**
s:property

**28.循环的struts标签是什么？**
s:iterator

**29.判断的strtuts标签是什么？**
s:if
<s:else>

**30.显示文本的strtuts标签是什么？**
<S:TEXTAREA>

**31.要调用struts标签需要导入什么？**

```markup
<%@taglib uri="/struts-tags" prefix="s"%>    
1
```

**32.如果需要调用sc:datetimepicker需要导入什么，sc代表的是什么意思？**

**33.<s:textfield>中的s代表的是什么，这个标签是干什么用的？**
获取前台文本的值

**34.请简述前台提交后是如何到达后台action的。**

通过from表单action找到struts配置文件相应action的类
再去找相应action类里的方法

**Struts2、Hibernate、Spring面试笔试题（含答案）
Hibernate工作原理及为什么要用？**
原理：
1.读取并解析配置文件
2.读取并解析映射信息，创建SessionFactory
3.打开Sesssion
4.创建事务Transation
5.持久化操作
6.提交事务
7.关闭Session
8.关闭SesstionFactory

**为什么要用：**

1. 对JDBC访问数据库的代码做了封装，大大简化了数据访问层繁琐的重复性代码。
2. Hibernate是一个基于JDBC的主流持久化框架，是一个优秀的ORM实现。
   他很大程度的简化DAO层的编码工作
3. hibernate使用Java反射机制，而不是字节码增强程序来实现透明性。
4. hibernate的性能非常好，因为它是个轻量级框架。映射的灵活性很出色。
   它支持各种关系数据库，从一对一到多对多的各种复杂关系。

**2． Hibernate是如何延迟加载?**

1. Hibernate2延迟加载实现：a)实体对象 b)集合（Collection）
2. Hibernate3 提供了属性的延迟加载功能

当Hibernate在查询数据的时候，数据并没有存在与内存中，当程序真正对数据的操作时，对象才存在与内存中，就实现了延迟加载，他节省了服务器的内存开销，从而提高了服务器的性能。

**3．Hibernate中怎样实现类之间的关系?(如：一对多、多对多的关系)**

类与类之间的关系主要体现在表与表之间的关系进行操作，它们都市对对象进行操作，我们程序中把所有的表与类都映射在一起，它们通过配置文件中的many-to-one、one-to-many、many-to-many、

**4． 说下Hibernate的缓存机制**

1. 内部缓存存在Hibernate中又叫一级缓存，属于应用事物级缓存
2. 二级缓存：
   a) 应用及缓存
   b) 分布式缓存
   条件：数据不会被第三方修改、数据大小在可接受范围、数据更新频率低、同一数据被系统频繁使用、非 关键数据
   c) 第三方缓存的实现

**5． Hibernate的查询方式**
Sql、Criteria,object comptosition
Hql：
1、 属性查询
2、 参数查询、命名参数查询
3、 关联查询
4、 分页查询
5、 统计函数

6． 如何优化Hibernate？
1.使用双向一对多关联，不使用单向一对多
2.灵活使用单向一对多关联
3.不用一对一，用多对一取代
4.配置对象缓存，不使用集合缓存
5.一对多集合使用Bag,多对多集合使用Set
\6. 继承类使用显式多态
\7. 表字段要少，表关联不要怕多，有二级缓存撑腰

7． Struts工作机制？为什么要使用Struts？
工作机制：
Struts的工作流程:
在web应用启动时就会加载初始化ActionServlet,ActionServlet从
struts-config.xml文件中读取配置信息,把它们存放到各种配置对象
当ActionServlet接收到一个客户请求时,将执行如下流程.
-(1)检索和用户请求匹配的ActionMapping实例,如果不存在,就返回请求路径无效信息;

-(2)如果ActionForm实例不存在,就创建一个ActionForm对象,把客户提交的表单数据保存到ActionForm对象中;

-(3)根据配置信息决定是否需要表单验证.如果需要验证,就调用ActionForm的validate()方法;

-(4)如果ActionForm的validate()方法返回null或返回一个不包含ActionMessage的ActuibErrors对象, 就表示表单验证成功;

-(5)ActionServlet根据ActionMapping所包含的映射信息决定将请求转发给哪个Action,如果相应的 Action实例不存在,就先创建这个实例,然后调用Action的execute()方法;

-(6)Action的execute()方法返回一个ActionForward对象,ActionServlet在把客户请求转发给 ActionForward对象指向的JSP组件;

-(7)ActionForward对象指向JSP组件生成动态网页,返回给客户;

为什么要用：
JSP、Servlet、JavaBean技术的出现给我们构建强大的企业应用系统提供了可能。但用这些技术构建的系统非常的繁乱，所以在此之上，我们需要一个规则、一个把这些技术组织起来的规则，这就是框架，Struts便应运而生。

基于Struts开发的应用由3类组件构成：控制器组件、模型组件、视图组件

8． Struts的validate框架是如何验证的？
在struts配置文件中配置具体的错误提示，再在FormBean中的validate()方法具体调用。

9． 说下Struts的设计模式
MVC 模式: web应用程序启动时就会加载并初始化ActionServler。用户提交表单时，一个配置好的ActionForm对象被创建，并被填入表单相应的数 据，ActionServler根据Struts-config.xml文件配置好的设置决定是否需要表单验证，如果需要就调用ActionForm的 Validate（）验证后选择将请求发送到哪个Action，如果Action不存在，ActionServlet会先创建这个对象，然后调用 Action的execute（）方法。Execute（）从ActionForm对象中获取数据，完成业务逻辑，返回一个ActionForward对 象，ActionServlet再把客户请求转发给ActionForward对象指定的jsp组件，ActionForward对象指定的jsp生成动 态的网页，返回给客户。

10． spring工作机制及为什么要用?
1.spring mvc请所有的请求都提交给DispatcherServlet,它会委托应用系统的其他模块负责负责对请求进行真正的处理工作。
2.DispatcherServlet查询一个或多个HandlerMapping,找到处理请求的Controller.
3.DispatcherServlet请请求提交到目标Controller
4.Controller进行业务逻辑处理后，会返回一个ModelAndView
5.Dispathcher查询一个或多个ViewResolver视图解析器,找到ModelAndView对象指定的视图对象
6.视图对象负责渲染返回给客户端。

为什么用：
{AOP 让开发人员可以创建非行为性的关注点，称为横切关注点，并将它们插入到应用程序代码中。使用 AOP 后，公共服务 （比 如日志、持久性、事务等）就可以分解成方面并应用到域对象上，同时不会增加域对象的对象模型的复杂性。
IOC 允许创建一个可以构造对象的应用环境，然后向这些对象传递它们的协作对象。正如单词 倒置 所表明的，IOC 就像反 过来的 JNDI。没有使用一堆抽象工厂、服务定位器、单元素（singleton）和直接构造（straight construction），每一个对象都是用其协作对象构造的。因此是由容器管理协作对象（collaborator）。
Spring即使一个AOP框架，也是一IOC容器。 Spring 最好的地方是它有助于您替换对象。有了 Spring，只要用 JavaBean 属性和配置文件加入依赖性（协作对象）。然后可以很容易地在需要时替换具有类似接口的协作对象。}

Spring 框架是一个分层架构，由 7 个定义良好的模块组成。Spring 模块构建在核心容器之上，核心容器定义了创建、配置和管理 bean 的方式，如图 1 所示。

组成 Spring 框架的每个模块（或组件）都可以单独存在，或者与其他一个或多个模块联合实现。每个模块的功能如下：

☆ 核心容器：核心容器提供 Spring 框架的基本功能。核心容器的主要组件是 BeanFactory，它是工厂模式的实现。BeanFactory 使用控制反转 （IOC）模式将应用程序的配置和依赖性规范与实际的应用程序代码分开。

☆ Spring 上下文：Spring 上下文是一个配置文件，向 Spring 框架提供上下文信息。Spring 上下文包括企业服务，例如 JNDI、EJB、电子邮件、国际化、校验和调度功能。

☆ Spring AOP：通过配置管理特性，Spring AOP 模块直接将面向方面的编程功能集成到了 Spring 框架中。所以，可以很容易地使 Spring 框架管理的任何对象支持 AOP。Spring AOP 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。通过使用 Spring AOP，不用依赖 EJB 组件，就可以将声明性事务管理集成到应用程序中。

☆ Spring DAO：JDBC DAO 抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理和不同数据库供应商抛出的错误消息。异常层次结构简化了错误处理，并且极大地降低了需要编写的异常代码数量（例如打开和关闭连接）。Spring DAO 的面向 JDBC 的异常遵从通用的 DAO 异常层次结构。

☆ Spring ORM：Spring 框架插入了若干个 ORM 框架，从而提供了 ORM 的对象关系工具，其中包括 JDO、Hibernate 和 iBatis SQL Map。所有这些都遵从 Spring 的通用事务和 DAO 异常层次结构。

☆ Spring Web 模块：Web 上下文模块建立在应用程序上下文模块之上，为基于 Web 的应用程序提供了上下文。所以，Spring 框架支持与 Jakarta Struts 的集成。Web 模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作。

☆ Spring MVC 框架：MVC 框架是一个全功能的构建 Web 应用程序的 MVC 实现。通过策略接口，MVC 框架变成为高度可配置的，MVC 容纳了大量视图技术，其中包括 JSP、Velocity、Tiles、iText 和 POI。

Spring 框架的功能可以用在任何 J2EE 服务器中，大多数功能也适用于不受管理的环境。Spring 的核心要点是：支持不绑定到特定 J2EE 服务的可重用业务和数据访问对象。毫无疑问，这样的对象可以在不同 J2EE 环境 （Web 或 EJB）、独立应用程序、测试环境之间重用。

IOC 和 AOP

控制反转模式（也称作依赖性介入）的基本概念是：不创建对象，但是描述创建它们的方式。在代码中不直接与对象和服务连接，但在配置文件中描述哪一个组件需要哪一项服务。容器（在 Spring 框架中是 IOC 容器） 负责将这些联系在一起。

在典型的 IOC 场景中，容器创建了所有对象，并设置必要的属性将它们连接在一起，决定什么时间调用方法。下表列出了 IOC 的一个实现模式。

Spring 框架的 IOC 容器采用类型 2 和类型3 实现。

面向方面的编程

面向方面的编程，即 AOP，是一种编程技术，它允许程序员对横切关注点或横切典型的职责分界线的行为（例如日志和事务管理）进行模块化。AOP 的核心构造是方面，它将那些影响多个类的行为封装到可重用的模块中。

AOP 和 IOC 是补充性的技术，它们都运用模块化方式解决企业应用程序开发中的复杂问题。在典型的面向对象开发方式中，可能要将日志记录语句放在所有方法和 Java 类中才能实现日志功能。在 AOP 方式中，可以反过来将日志服务模块化，并以声明的方式将它们应用到需要日志的组件上。当然，优势就是 Java 类不需要知道日志服务的存在，也不需要考虑相关的代码。所以，用 Spring AOP 编写的应用程序代码是松散耦合的。

AOP 的功能完全集成到了 Spring 事务管理、日志和其他各种特性的上下文中。

IOC 容器

Spring 设计的核心是 org.springframework.beans 包，它的设计目标是与 JavaBean 组件一起使用。这个包通常不是由用户直接使用，而是由服务器将其用作其他多数功能的底层中介。下一个最高级抽象是 BeanFactory 接口，它是工厂设计模式的实现，允许通过名称创建和检索对象。BeanFactory 也可以管理对象之间的关系。

BeanFactory 支持两个对象模型。

□ 单态 模型提供了具有特定名称的对象的共享实例，可以在查询时对其进行检索。Singleton 是默认的也是最常用的对象模型。对于无状态服务对象很理想。

□ 原型 模型确保每次检索都会创建单独的对象。在每个用户都需要自己的对象时，原型模型最适合。

bean 工厂的概念是 Spring 作为 IOC 容器的基础。IOC 将处理事情的责任从应用程序代码转移到框架。正如我将在下一个示例中演示的那样，Spring 框架使用 JavaBean 属性和配置数据来指出必须设置的依赖关系。

BeanFactory 接口

因为 org.springframework.beans.factory.BeanFactory 是一个简单接口，所以可以针对各种底层存储方法实现。最常用的 BeanFactory 定义是 XmlBeanFactory，它根据 XML 文件中的定义装入 bean，如清单 1 所示。

清单 1. XmlBeanFactory

BeanFactory factory = new XMLBeanFactory(new FileInputSteam(“mybean.xml”));

在 XML 文件中定义的 Bean 是被消极加载的，这意味在需要 bean 之前，bean 本身不会被初始化。要从 BeanFactory 检索 bean，只需调用 getBean() 方法，传入将要检索的 bean 的名称即可，如清单 2 所示。

清单 2. getBean()

MyBean mybean = (MyBean) factory.getBean(“mybean”);

每个 bean 的定义都可以是 POJO （用类名和 JavaBean 初始化属性定义） 或 FactoryBean。FactoryBean 接口为使用 Spring 框架构建的应用程序添加了一个间接的级别。

IOC 示例

理解控制反转最简单的方式就是看它的实际应用。在对由三部分组成的 Spring 系列 的第 1 部分进行总结时，我使用了一个示例，演示了如何通过 Spring IOC 容器注入应用程序的依赖关系（而不是将它们构建进来）。

我用开启在线信用帐户的用例作为起点。对于该实现，开启信用帐户要求用户与以下服务进行交互：

☆ 信用级别评定服务，查询用户的信用历史信息。

☆ 远程信息链接服务，插入客户信息，将客户信息与信用卡和银行信息连接起来，以进行自动借记（如果需要的话）。

☆ 电子邮件服务，向用户发送有关信用卡状态的电子邮件。

三个接口

对于这个示例，我假设服务已经存在，理想的情况是用松散耦合的方式把它们集成在一起。以下清单显示了三个服务的应用程序接口。

清单 3. CreditRatingInterface

public interface CreditRatingInterface {
public boolean getUserCreditHistoryInformation(ICustomer iCustomer);

}

清单 3 所示的信用级别评定接口提供了信用历史信息。它需要一个包含客户信息的 Customer 对象。该接口的实现是由 CreditRating 类提供的。

清单 4. CreditLinkingInterface

public interface CreditLinkingInterface {
public String getUrl();
public void setUrl(String url);
public void linkCreditBankAccount() throws Exception ;
}

信用链接接口将信用历史信息与银行信息（如果需要的话）连接在一起，并插入用户的信用卡信息。信用链接接口是一个远程服务，它的查询是通过 getUrl() 方法进行的。URL 由 Spring 框架的 bean 配置机制设置，我稍后会讨论它。该接口的实现是由 CreditLinking 类提供的。

清单 5. EmailInterface

public interface EmailInterface {
public void sendEmail(ICustomer iCustomer);
public String getFromEmail();
public void setFromEmail(String fromEmail) ;
public String getPassword();
public void setPassword(String password) ;
public String getSmtpHost() ;
public void setSmtpHost(String smtpHost);
public String getUserId() ;
public void setUserId(String userId);

Struts2面试题

1、struts2工作流程

Struts 2框架本身大致可以分为3个部分：
核心控制器FilterDispatcher、业务控制器Action和用户实现的企业业务逻辑组件。

核心控制器FilterDispatcher是Struts 2框架的基础，
包含了框架内部的控制流程和处理机制。
业务控制器Action和业务逻辑组件是需要用户来自己实现的。
用户在开发Action和业务逻辑组件的同时，还需要编写相关的配置文件，
供核心控制器FilterDispatcher来使用。
Struts 2的工作流程相对于Struts 1要简单，与WebWork框架基本相同，

所以说Struts 2是WebWork的升级版本。基本简要流程如下：
1 、客户端初始化一个指向Servlet容器的请求；
2、 这个请求经过一系列的过滤器（Filter）
（这些过滤器中有一个叫做ActionContextCleanUp的可选过滤器，
这个过滤器对于Struts2和其他框架的集成很有帮助，例如：SiteMesh Plugin）
3 、接着FilterDispatcher被调用，
FilterDispatcher询问ActionMapper来决定这个请是否需要调用某个Action
4、如果ActionMapper决定需要调用某个Action，
FilterDispatcher把请求的处理交给ActionProxy
5、ActionProxy通过Configuration Manager询问框架的配置文件，
找到需要调用的Action类
6、ActionProxy创建一个ActionInvocation的实例。
7、ActionInvocation实例使用命名模式来调用，
在调用Action的过程前后，涉及到相关拦截器（Intercepter）的调用。
8、一旦Action执行完毕，ActionInvocation负责根据struts.xml中的配置找到对应的返回结果 。返回结果通常是（但不总是，也可 能是另外的一个Action链）一个需要被表示的JSP或者FreeMarker的模版。 在表示的过程中可以使用Struts2 框架中继承的标签。 在这个过程中需要涉及到ActionMapper
9、响应的返回是通过我们在web.xml中配置的过滤器
10、如果ActionContextCleanUp是当前使用的，则FilterDispatecher将不会清理sreadlocal ActionContext;如果ActionContextCleanUp不使用，则将会去清理sreadlocals。

2、说下Struts的设计模式

MVC模式: web应用程序启动时就会加载并初始化ActionServler。用户提交表单时，一个配置好的ActionForm对象被创建，并被填入表单相应的数据，ActionServler根据Struts-config.xml文件配置好的设置决定是否需要表单验证，如果需要就调用ActionForm的Validate（）验证后选择将请求发送到哪个Action，如果Action不存在，ActionServlet会先创建这个对象，然后调用Action的execute（）方法。Execute（）从ActionForm对象中获取数据，完成业务逻辑，返回一个ActionForward对象，ActionServlet再把客户请求转发给ActionForward对象指定的jsp组件，ActionForward对象指定的jsp生
成动态的网页，返回给客户。

3、拦截器和过滤器的区别

1、拦截器是基于java反射机制的，而过滤器是基于函数回调的。
2、过滤器依赖于servlet容器，而拦截器不依赖于servlet容器。
3、拦截器只能对Action请求起作用，而过滤器则可以对几乎所有请求起作用。
4、拦截器可以访问Action上下文、值栈里的对象，而过滤器不能。
5、在Action的生命周期中，拦截器可以多次调用，而过滤器只能在容器初始化时被调用一次。

4、struts1于struts2的比较

1、Action 类:
Struts1要求Action类继承一个抽象基类。Struts1的一个普遍问题是使用抽象类编程而不是接口。
Struts 2 Action类可以实现一个Action接口，也可实现其他接口，使可选和定制的服务成为可能。Struts2提供一个ActionSupport基类去 实现常用的接口。Action接口不是必须的，任何有execute标识的POJO对象都可以用作Struts2的Action对象。

2、线程模式:
Struts1 Action是单例模式并且必须是线程安全的，因为仅有Action的一个实例来处理所有的请求。单例策略限制了Struts1 Action能作的事，并且要在开发时特别小心。Action资源必须是线程安全的或同步的。
Struts2 Action对象为每一个请求产生一个实例，因此没有线程安全问题。（实际上，servlet容器给每个请求产生许多可丢弃的对象，并且不会导致性能和垃圾回收问题）

3、Servlet 依赖:
Struts1 Action 依赖于Servlet API ,因为当一个Action被调用时HttpServletRequest 和 HttpServletResponse 被传递给execute方法。
Struts 2 Action不依赖于容器，允许Action脱离容器单独被测试。如果需要，Struts2 Action仍然可以访问初始的request和response。但是，其他的元素减少或者消除了直接访问HttpServetRequest 和 HttpServletResponse的必要性。

4、可测性:
测试Struts1 Action的一个主要问题是execute方法暴露了servlet API（这使得测试要依赖于容器）。一个第三方扩展－－Struts TestCase－－提供了一套Struts1的模拟对象（来进行测试）。
Struts 2 Action可以通过初始化、设置属性、调用方法来测试，“依赖注入”支持也使测试更容易。

5、捕获输入:
Struts1 使用ActionForm对象捕获输入。所有的ActionForm必须继承一个基类。因为其他JavaBean不能用作ActionForm，开发者经 常创建多余的类捕获输入。动态Bean（DynaBeans）可以作为创建传统ActionForm的选择，但是，开发者可能是在重新描述(创建)已经存 在的JavaBean（仍然会导致有冗余的javabean）。
Struts 2直接使用Action属性作为输入属性，消除了对第二个输入对象的需求。输入属性可能是有自己(子)属性的rich对象类型。Action属性能够通过 web页面上的taglibs访问。Struts2也支持ActionForm模式。rich对象类型，包括业务对象，能够用作输入/输出对象。这种 ModelDriven 特性简化了taglib对POJO输入对象的引用。

6、表达式语言：
Struts1 整合了JSTL，因此使用JSTL EL。这种EL有基本对象图遍历，但是对集合和索引属性的支持很弱。
Struts2可以使用JSTL，但是也支持一个更强大和灵活的表达式语言－－ "Object Graph Notation Language " (OGNL).

7、绑定值到页面（view）:
Struts 1使用标准JSP机制把对象绑定到页面中来访问。
Struts 2 使用 "ValueStack "技术，使taglib能够访问值而不需要把你的页面（view）和对象绑定起来。ValueStack策略允许通过一系列名称相同但类型不同的属性重用页面（view）。

8、类型转换：
Struts 1 ActionForm 属性通常都是String类型。Struts1使用Commons-Beanutils进行类型转换。每个类一个转换器，对每一个实例来说是不可配置的。
Struts2 使用OGNL进行类型转换。提供基本和常用对象的转换器。

9、校验：
Struts 1支持在ActionForm的validate方法中手动校验，或者通过Commons Validator的扩展来校验。同一个类可以有不同的校验内容，但不能校验子对象。
Struts2支持通过validate方法和XWork校验框架来进行校验。XWork校验框架使用为属性类类型定义的校验和内容校验，来支持chain校验子属性

10、Action执行的控制：
Struts1支持每一个模块有单独的Request Processors（生命周期），但是模块中的所有Action必须共享相同的生命周期。
Struts2支持通过拦截器堆栈（Interceptor Stacks）为每一个Action创建不同的生命周期。堆栈能够根据需要和不同的Action一起使用。

为什么要使用Struts2

Struts2 是一个相当强大的Java Web开源框架，是一个基于POJO的Action的MVC Web框架。它基于当年的Webwork和XWork框架，继承其优点，同时做了相当的改进。

1.Struts2基于MVC架构，框架结构清晰，开发流程一目了然，开发人员可以很好的掌控开发的过程。
2使用OGNL进行参数传递。
OGNL提供了在Struts2里访问各种作用域中的数据的简单方式，你可以方便的获取Request，Attribute，Application，Session，Parameters中的数据。大大简化了开发人员在获取这些数据时的代码量。
3强大的拦截器
Struts2 的拦截器是一个Action级别的AOP，Struts2中的许多特性都是通过拦截器来实现的，例如异常处理，文件上传，验证等。拦截器是可配置与重用的，可以将一些通用的功能如：登录验证，权限验证等置于拦截器中以完成一些Java Web项目中比较通用的功能。在我实现的的一Web项目中，就是使用Struts2的拦截器来完成了系统中的权限验证功能。
4易于测试
Struts2的Action都是简单的POJO，这样可以方便的对Struts2的Action编写测试用例，大大方便了5Java Web项目的测试。
易于扩展的插件机制在Struts2添加扩展是一件愉快而轻松的事情，只需要将所需要的Jar包放到WEB-INF/lib文件夹中，在struts.xml中作一些简单的设置就可以实现扩展。
6模块化管理
Struts2已经把模块化作为了体系架构中的基本思想，可以通过三种方法来将应用程序模块化：将配置信息拆分成多个文件把自包含的应用模块创建为插件创建新的框架特性，即将与特定应用无关的新功能组织成插件，以添加到多个应用中去。
7全局结果与声明式异常
为应用程序添加全局的Result，和在配置文件中对异常进行处理，这样当处理过程中出现指定异常时，可以跳转到特定页面。
他的如此之多的优点，是很多人比较的青睐，与spring ,Hibernate进行结合，组成了现在比较流行的ssh框架，当然每个公司都要自己的框架，也是ssh变异的产品。

struts2有哪些优点？

1）在软件设计上Struts2的应用可以不依赖于Servlet API和struts API。 Struts2的这种设计属于无侵入式设计；
2）拦截器，实现如参数拦截注入等功能；
3）类型转换器，可以把特殊的请求参数转换成需要的类型；
4）多种表现层技术，如：JSP、freeMarker、Velocity等；
5）Struts2的输入校验可以对指定某个方法进行校验；
6）提供了全局范围、包范围和Action范围的国际化资源文件管理实现

struts2是如何启动的？

struts2框架是通过Filter启动的，即StrutsPrepareAndExecuteFilter，此过滤器为struts2的核心过滤器；
StrutsPrepareAndExecuteFilter的init()方法中将会读取类路径下默认的配置文件struts.xml完成初始化操作。struts2读取到struts.xml的内容后，是将内容封装进javabean对象然后存放在内存中，以后用户的每次请求处理将使用内存中的数据，而不是每次请求都读取struts.xml文件。

struts2框架的核心控制器是什么？它有什么作用？

1）Struts2框架的核心控制器是StrutsPrepareAndExecuteFilter。
2）作用：
负责拦截由/*指定的所有用户请求，当用户请求到达时，该Filter会过滤用户的请求。默认情况下，如果用户请求的路径
不带后缀或者后缀以.action结尾，这时请求将被转入struts2框架处理，否则struts2框架将略过该请求的处理。
可以通过常量"struts.action.extension"修改action的后缀，如：

如果用户需要指定多个请求后缀，则多个后缀之间以英文逗号（,）隔开。

struts2配置文件的加载顺序？

struts.xml ——> struts.properties
常量可以在struts.xml或struts.properties中配置，如果在多个文件中配置了同一个常量，则后一个文件中配置的常量值会覆盖前面文件中配置的常量值.
struts.xml文件的作用：通知Struts2框架加载对应的Action资源

struts2常量的修改方式？

常量可以在struts.xml或struts.properties中配置，两种配置方式如下：
1）在struts.xml文件中配置常量

2）在struts.properties中配置常量（struts.properties文件放置在src下）：
struts.action.extension=do

struts2如何访问HttpServletRequest、HttpSession、ServletContext三个域对象？

方案一：
HttpServletRequest request =ServletActionContext.getRequest();
HttpServletResponse response =ServletActionContext.getResponse();
HttpSession session= request.getSession();
ServletContext servletContext=ServletActionContext.getServletContext();

方案二：
类 implements ServletRequestAware,ServletResponseAware，SessionAware，ServletContextAware
注意：框架自动传入对应的域对象

struts2是如何管理action的？这种管理方式有什么好处？

struts2框架中使用包来管理Action，包的作用和java中的类包是非常类似的。
主要用于管理一组业务功能相关的action。在实际应用中，我们应该把一组业务功能相关的Action放在同一个包下。

struts2中的默认包struts-default有什么作用？

1）struts-default包是由struts内置的，它定义了struts2内部的众多拦截器和Result类型，而Struts2很多核心的功能都是通过这些内置的拦截器实现，如：从请求中
把请求参数封装到action、文件上传和数据验证等等都是通过拦截器实现的。当包继承了struts-default包才能使用struts2为我们提供的这些功能。
2）struts-default包是在struts-default.xml中定义，struts-default.xml也是Struts2默认配置文件。 Struts2每次都会自动加载 struts-default.xml文件。
3）通常每个包都应该继承struts-default包。

struts2如何对指定的方法进行验证？

1）validate()方法会校验action中所有与execute方法签名相同的方法；
2）要校验指定的方法通过重写validateXxx()方法实现， validateXxx()只会校验action中方法名为Xxx的方法。其中Xxx的第一个字母要大写；
3）当某个数据校验失败时，调用addFieldError()方法往系统的fieldErrors添加校验失败信息（为了使用addFieldError()方法，action可以继承ActionSupport）， 如果系统 的fieldErrors包含失败信息，struts2会将请求转发到名为input的result；
4）在input视图中可以通过<s:fielderror/>显示失败信息。
5）先执行validateXxxx()->validate()->如果出错了，会转发所指定的页面，如果不出错，会直接进行Action::execute()方法

struts2默认能解决get和post提交方式的乱码问题吗？

不能。struts.i18n.encoding=UTF-8属性值只能解析POST提交下的乱码问题。
String str = new String (new byte(),””,””);

请你写出struts2中至少5个的默认拦截器？

fileUpload 提供文件上传功能
i18n 记录用户选择的locale
cookies 使用配置的name,value来是指cookies
checkbox 添加了checkbox自动处理代码，将没有选中的checkbox的内容设定为false，而html默认情况下不提交没有选中的checkbox。
chain 让前一个Action的属性可以被后一个Action访问，现在和chain类型的result（）结合使用。
alias 在不同请求之间将请求参数在不同名字件转换，请求内容不变

值栈ValueStack的原理与生命周期？

1）ValueStack贯穿整个 Action 的生命周期，保存在request域中，所以ValueStack和request的生命周期一样。当Struts2接受一个请求时，会迅速创建ActionContext，
ValueStack，action。然后把action存放进ValueStack，所以action的实例变量可以被OGNL访问。 请求来的时候，action、ValueStack的生命开始，请求结束，action、 ValueStack的生命结束；
2）action是多例的，和Servlet不一样，Servelt是单例的；
3）每个action的都有一个对应的值栈，值栈存放的数据类型是该action的实例，以及该action中的实例变量，Action对象默认保存在栈顶；
4）ValueStack本质上就是一个ArrayList；
5）关于ContextMap，Struts 会把下面这些映射压入 ContextMap 中：
parameters : 该 Map 中包含当前请求的请求参数
request : 该 Map 中包含当前 request 对象中的所有属性 session :该 Map 中包含当前 session 对象中的所有属性
application :该 Map 中包含当前 application 对象中的所有属性
attr:该 Map 按如下顺序来检索某个属性: request, session, application
6）使用OGNL访问值栈的内容时，不需要#号，而访问request、session、application、attr时，需要加#号；
7）注意： Struts2中，OGNL表达式需要配合Struts标签才可以使用。如：<s:property value=“name”/>
8）在struts2配置文件中引用ognl表达式 ,引用值栈的值 ，此时使用的"$"，而不是#或者%;

ActionContext、ServletContext、pageContext的区别？

1）ActionContext是当前的Action的上下文环境，通过ActionContext可以获取到request、session、ServletContext等与Action有关的对象的引用；
2）ServletContext是域对象，一个web应用中只有一个ServletContext，生命周期伴随整个web应用；
3）pageContext是JSP中的最重要的一个内置对象，可以通过pageContext获取其他域对象的应用，同时它是一个域对象，作用范围只针对当前页面，当前页面结束时，pageContext销毁，
生命周期是JSP四个域对象中最小的。

result的type属性中有哪几种结果类型？

一共10种：
dispatcher
struts默认的结果类型，把控制权转发给应用程序里的某个资源不能把控制权转发给一个外部资源，若需要把控制权重定向到一个外部资源, 应该使用
redirect结果类型
redirect 把响应重定向到另一个资源（包括一个外部资源）
redirectAction 把响应重定向到另一个 Action
freemarker、velocity、chain、httpheader、xslt、plainText、stream

拦截器的生命周期与工作过程？

1）每个拦截器都是实现了Interceptor接口的 Java 类；
2）init(): 该方法将在拦截器被创建后立即被调用, 它在拦截器的生命周期内只被调用一次. 可以在该方法中对相关资源进行必要的初始化；
3）intercept(ActionInvocation invocation): 每拦截一个动作请求, 该方法就会被调用一次；
4）destroy: 该方法将在拦截器被销毁之前被调用, 它在拦截器的生命周期内也只被调用一次；
5）struts2中有内置了18个拦截器。

struts2如何完成文件的上传？

1、JSP页面：
1）JSP页面的上传文件的组件：<s: file name=”upload” />，如果需要一次上传多个文件, 就必须使用多个 file 标签, 但它们的名字必须是相同的，即：
name=“xxx”的值必须一样；
2）必须把表单的enctype属性设置为：multipart/form-data；
3）表单的方法必须为post，因为post提交的数据在消息体中，而无大小限制。
2、对应的action：
1）在 Action 中新添加 3 个和文件上传相关的属性；
2）如果是上传单个文件, uploadImage属性的类型就是 java.io.File, 它代表被上传的文件, 第二个和第三个属性的类型是 String, 它们分别代表上传文
件的文件名和文件类型，定义方式是分别是：
jsp页面file组件的名称+ContentType, jsp页面file组件的名称+FileName
3）如果上上传多个文件, 可以使用数组或 List

struts的工作原理

1、初始化，读取struts-config.xml、web.xml等配置文件（所有配置文件的初始化）
2、发送HTTP请求,客户端发送以.do结尾的请求
3、填充FormBean（实例化、复位、填充数据、校验、保存）
4、将请求转发到Action（调用Action的execute（）方法）
5、处理业务（可以调用后台类，返回ActionForward对象）
6、返回目标响应对象（从Action返回到ActionServlet）
7、转换Http请求到目标响应对象（查找响应，根据返回的Forward keyword）
8、Http响应，返回到Jsp页面

用自己的话简要阐述struts2的执行流程。
Struts 2框架本身大致可以分为3个部分：核心控制器FilterDispatcher、业务控制器Action和用户实现的企业业务逻辑组件。

核心控制器FilterDispatcher是Struts 2框架的基础，包含了框架内部的控制流程和处理机制。

业务控制器Action和业务逻辑组件是需要用户来自己实现的。用户在开发Action和业务逻辑组件的同时，还需要编写相关的配置文件，供核心控制器FilterDispatcher来使用。

Struts 2的工作流程相对于Struts 1要简单，与WebWork框架基本相同，所以说Struts 2是WebWork的升级版本。基本简要流程如下：

1、客户端浏览器发出HTTP请求。

2、根据web.xml配置，该请求被FilterDispatcher接收。

3、根据struts.xml配置，找到需要调用的Action类和方法， 并通过IoC方式，将值注入给Aciton。

4、Action调用业务逻辑组件处理业务逻辑，这一步包含表单验证。

5、Action执行完毕，根据struts.xml中的配置找到对应的返回结果result，并跳转到相应页面。

6、返回HTTP响应到客户端浏览器。

它是以Webwork的设计思想为核心，吸收struts1的优点，可以说 struts2是struts1和Webwork结合的产物。 struts2 的工作原理图： 一个请求在Struts2框架中的处理分为以下几个步骤： 1.客户端发出一个指向servlet容器的请求(tomcat)； 2.这个请求会经过图中的几个过滤器，最后会到达FilterDispatcher过滤器。 3.过滤器FilterDispatcher是struts2框架的心脏，在处理用户请求时，它和请求一起相互配合访问struts2 的底层框架结构。在web容器启动时，struts2框架会自动加载配置文件里相关参数，并转换成相应的类。 如：ConfigurationManager、ActionMapper和ObjectFactory。ConfigurationManager 存有配置文件的一 些基本信息，ActionMapper存有action的配置信息。在请求过程中所有的对象（Action，Results， Interceptors，等）都是通过ObjectFactory来创建的。过滤器会通过询问ActionMapper类来查找请求中 需要用到的Action。 4.如果找到需要调用的Action，过滤器会把请求的处理交给ActionProxy。ActionProxy为Action的代理对象 。ActionProxy通过ConfigurationManager询问框架的配置文件，找到需要调用的Action类。 5.ActionProxy创建一个ActionInvocation的实例。ActionInvocation在ActionProxy层之下，它表示了 Action的执行状态,或者说它控制的Action的执行步骤。它持有Action实例和所有的Interceptor。 6.ActionInvocation实例使用命名模式来调用，1. ActionInvocation初始化时，根据配置，加载Action相 关的所有Interceptor。2. 通过ActionInvocation.invoke方法调用Action实现时，执行Interceptor。在 调用Action的过程前后，涉及到相关拦截器(intercepetor)的调用。 7. 一旦Action执行完毕，ActionInvocation负责根据struts.xml中的配置找到对应的返回结果。返回结果 通常是（但不总是，也可能是另外的一个Action链）一个需要被表示的JSP或者FreeMarker的模版。在表 示的过程中可以使用Struts2 框架中继承的标签。

### 参考资料

1. https://blog.csdn.net/weixin_45755062/article/details/102576536
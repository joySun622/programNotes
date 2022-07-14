[Toc]

## 简介

>  Spring Boot实现了自动配置，降低了项目搭建的复杂度。集成了大量常用的第三方库配置(例如Jackson, JDBC, Mongo, Redis, Mail等等)，Spring Boot应用中这些第三方库几乎可以零配置的开箱即用(out-of-the-box)，大部分的Spring Boot应用都只需要非常少量的配置代码，开发者能够更加专注于业务逻辑。
>
> Intellij IDEA 一般可以通过两种方式创建 Spring Boot 项目：
>
> - 使用 Maven 创建
>
> - 使用 Spring Initializr 创建
>
>   
>
> **注意**：Spring Boot 内部集成了 Tomcat，不需要人为手动配置 Tomcat，开发者只需要关注具体的业务逻辑即可。

## starter

> Spring Boot 将日常企业应用研发中的各种场景都抽取出来，做成一个个的 starter（启动器），starter 中整合了该场景下各种可能用到的依赖，用户只需要在 Maven 中引入 starter 依赖，SpringBoot 就能自动扫描到要加载的信息并启动相应的默认配置。starter 提供了大量的自动配置，让用户摆脱了处理各种依赖和配置的困扰。所有这些 starter 都遵循着约定成俗的默认配置，并允许用户调整这些配置，即遵循“约定大于配置”的原则。
>
> > 并不是所有的 starter 都是由 Spring Boot 官方提供的，也有部分 starter 是第三方技术厂商提供的，例如 druid-spring-boot-starter 和 mybatis-spring-boot-starter 等等。当然也存在个别第三方技术，Spring Boot 官方没提供 starter，第三方技术厂商也没有提供 starter。

## 全局配置文件

> SpringBoot 默认使用以下 2 种全局的配置文件，其文件名是固定的。
>
> - application.properties
> - application.yml
>
> 其中，application.yml 是一种使用 YAML 语言编写的文件，它与 application.properties 一样，可以在 Spring Boot 启动时被自动读取，修改 Spring Boot 自动配置的默认值。

## Spring Boot配置绑定

> 所谓“配置绑定”就是把配置文件中的值与 JavaBean 中对应的属性进行绑定。通常，我们会把一些配置信息（例如，数据库配置）放在配置文件中，然后通过 Java 代码去读取该配置文件，并且把配置文件中指定的配置封装到 JavaBean（实体类） 中。
>
> SpringBoot 提供了以下 2 种方式进行配置绑定：
>
> - 使用 @ConfigurationProperties 注解
> - 使用 @Value 注解

### @ConfigurationProperties

> 通过 Spring Boot 提供的 @ConfigurationProperties 注解，可以将全局配置文件中的配置数据绑定到 JavaBean 中。
>
> **注意**：
>
> - 只有在容器中的组件，才会拥有 SpringBoot 提供的强大功能。如果我们想要使用 @ConfigurationProperties 注解进行配置绑定，那么首先就要保证该对 JavaBean 对象在 IoC 容器中，所以需要用到 @Component 注解来添加组件到容器中。
> - JavaBean 上使用了注解 @ConfigurationProperties(prefix = "person") ，它表示将这个 JavaBean 中的所有属性与配置文件中以“person”为前缀的配置进行绑定。
>
> ```
> @Component
> @ConfigurationProperties(prefix = "person")
> public class Person {
>     private String lastName;
>     ......
>  }
>     
> ```
>
> 

### @Value

> 我们只需要读取配置文件中的某一个配置时，可以通过 @Value 注解获取。
>
> ```
> @Component
> public class Person {
>     @Value("${person.lastName}")
>     private String lastName;
>     }
> ```

### @Value 与 @ConfigurationProperties 对比

@Value 和 @ConfigurationProperties 注解都能读取配置文件中的属性值并绑定到 JavaBean 中，但两者存在以下不同。

#### 1. 使用位置不同

- @ConfigurationProperties：标注在 JavaBean 的类名上；
- @Value：标注在 JavaBean 的属性上。

#### 2. 功能不同

- @ConfigurationProperties：用于批量绑定配置文件中的配置；
- @Value：只能一个一个的指定需要绑定的配置。

#### 3. 松散绑定支持不同

@ConfigurationProperties：支持松散绑定（松散语法），例如实体类 Person 中有一个属性为 lastName，那么配置文件中的属性名支持以下写法：

- person.firstName
- person.first-name
- person.first_name
- PERSON_FIRST_NAME


@Vaule：不支持松散绑定。

#### 4. SpEL 支持不同

- @ConfigurationProperties：不支持 SpEL 表达式;
- @Value：支持 SpEL 表达式。

#### 5. 复杂类型封装

- @ConfigurationProperties：支持所有类型数据的封装，例如 Map、List、Set、以及对象等；
- @Value：只支持基本数据类型的封装，例如字符串、布尔值、整数等类型。

#### 6. 应用场景不同

@Value 和 @ConfigurationProperties 两个注解之间，并没有明显的优劣之分，它们只是适合的应用场景不同而已。

- 若只是获取配置文件中的某项值，则推荐使用 @Value 注解；
- 若专门编写了一个 JavaBean 来和配置文件进行映射，则建议使用 @ConfigurationProperties 注解。

### @PropertySource 

> 如果将所有的配置都集中到 application.properties 或 application.yml 中，那么这个配置文件会十分的臃肿且难以维护，因此我们通常会将与 Spring Boot 无关的配置（例如自定义配置）提取出来，写在一个单独的配置文件中，并在对应的 JavaBean 上使用 @PropertySource 注解指向该配置文件。

```
将与 person 相关的自定义配置移动到 src/main/resources 下的 person.properties 中

@PropertySource(value = "classpath:person.properties")//指向对应的配置文件
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String lastName;
    
    }
```

## Spring Boot导入Spring配置

> 默认情况下，Spring Boot 中是不包含任何的 Spring 配置文件的，即使我们手动添加 Spring 配置文件到项目中，也不会被识别。那么 Spring Boot 项目中真的就无法导入 Spring 配置吗？答案是否定的。
>
> Spring Boot 为了我们提供了以下 2 种方式来导入 Spring 配置：
>
> - 使用 @ImportResource 注解加载 Spring 配置文件
> - 使用全注解方式加载 Spring 配置

### @ImportResource 导入 Spring 配置文件

> 在主启动类上使用 @ImportResource 注解可以导入一个或多个 Spring 配置文件，并使其中的内容生效。
>
> ```
> //将 beans.xml 加载到项目中
> @ImportResource(locations = {"classpath:/beans.xml"})
> @SpringBootApplication
> public class HelloworldApplication {
>     public static void main(String[] args) {
>         SpringApplication.run(HelloworldApplication.class, args);
>     }
> }
> ```

### 全注解方式加载 Spring 配置

> Spring Boot 推荐我们使用全注解的方式加载 Spring 配置，其实现方式如下：
>
> 1. 使用 @Configuration 注解定义配置类，替换 Spring 的配置文件；
> 2. 配置类内部可以包含有一个或多个被 @Bean 注解的方法，这些方法会被 AnnotationConfigApplicationContext 或 AnnotationConfigWebApplicationContext 类扫描，构建 bean 定义（相当于 Spring 配置文件中的<bean></bean>标签），方法的返回值会以组件的形式添加到容器中，组件的 id 就是方法名。
>
> ```
> /**
> * @Configuration 注解用于定义一个配置类，相当于 Spring 的配置文件
> * 配置类中包含一个或多个被 @Bean 注解的方法，该方法相当于 Spring 配置文件中的 <bean> 标签定义的组件。
> */
> @Configuration
> public class MyAppConfig {
>     /**
>      * 与 <bean id="personService" class="PersonServiceImpl"></bean> 等价
>      * 该方法返回值以组件的形式添加到容器中
>      * 方法名是组件 id（相当于 <bean> 标签的属性 id)
>      */
>     @Bean
>     public PersonService personService() {
>         System.out.println("在容器中添加了一个组件:peronService");
>         return new PersonServiceImpl();
>     }
> }
> ```

## Spring Boot默认配置文件

> 通常情况下，Spring Boot 在启动时会将 resources 目录下的 application.properties 或 apllication.yml 作为其默认配置文件，我们可以在该配置文件中对项目进行配置，但这并不意味着 Spring Boot 项目中只能存在一个 application.properties 或 application.yml。
>
> ```
> Spring Boot 项目中可以存在多个 application.properties 或 apllication.yml。
> 
> Spring Boot 启动时会扫描以下 5 个位置的  application.properties 或 apllication.yml 文件，并将它们作为 Spring boot 的默认配置文件。
> file:./config/
> file:./config/*/
> file:./
> classpath:/config/
> classpath:/
> 注：file: 指当前项目根目录；classpath: 指当前项目的类路径，即 resources 目录。
> 
> 以上所有位置的配置文件都会被加载，且它们优先级依次降低，序号越小优先级越高。其次，位于相同位置的 application.properties 的优先级高于 application.yml。
> 
> 所有位置的文件都会被加载，高优先级配置会覆盖低优先级配置，形成互补配置，即：
> 存在相同的配置内容时，高优先级的内容会覆盖低优先级的内容；
> 存在不同的配置内容时，高优先级和低优先级的配置内容取并集。
> 
> ```
>

## 常用注解

### @SpringBootApplication

> `@SpringBootAppliction`等同于同时使用`@Configuration @EnableAutoConfiguration @ComponentScan`这三个注解的默认属性。
>
> - **@Configuration**
>
> ```
> 功能：
> 1. @SpringBootConfiguration继承自@Configuration，二者功能也一致，标注当前类是配置类，
> 2. 并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到spring容器中，并且实例名就是方法名。
> 
> @Bean：用@Bean标注方法等价于XML中配置的bean。
> @Value：注入Spring boot application.properties配置的属性的值。示例代码：
> @Value(value= “#{message}”)privateString message;
> ```
>
> - **@EnableAutoConfiguration**
>
> ```
> ### 功能
> 借助@Import的支持，收集和注册特定场景相关的bean定义。将所有符合自动配置条件的bean定义加载到IoC容器。帮助SpringBoot应用将所有符合条件的@Configuration配置都加载到当前SpringBoot创建并使用的IoC容器。
> 它是一个集合注解：
> @Target({ElementType.TYPE})
> @Retention(RetentionPolicy.RUNTIME)
> @Documented
> @Inherited
> @AutoConfigurationPackage
> @Import({AutoConfigurationImportSelector.class})
> public @interface EnableAutoConfiguration {
>     String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";
> 
>     Class<?>[] exclude() default {};
> 
>     String[] excludeName() default {};
> }
> 
> 使用方式：与@Configuration一起使用
> 
> ```
>
> - **@ConponentScan**
>
> ```
> ### 功能
> 扫描当前包下的子包下被@Component，@Controller，@Service，@Repository注解标记的类并纳入到spring容器中进行管理。等价于<context:component-scan>的xml配置文件中的配置项。
> 
> ### 参数作用
> basePackageClasses：对basepackages()指定扫描注释组件包类型安全的替代。 
> excludeFilters：指定不适合组件扫描的类型。
> includeFilters：指定哪些类型有资格用于组件扫描。 
> lazyInit：指定是否应注册扫描的beans为lazy初始化。 
> nameGenerator：用于在Spring容器中的检测到的组件命名。
> resourcePattern：控制可用于组件检测的类文件。 
> scopedProxy：指出代理是否应该对检测元件产生，在使用过程中会在代理风格时尚的范围是必要的。 
> scopeResolver：用于解决检测到的组件的范围。 
> useDefaultFilters：指示是否自动检测类的注释 
> 
> ### 范例
> @ComponentScan(excludeFilters = {
> 		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
> 		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
> ```

### **@ServletComponentScan**

```
Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，这样通过注解servlet ，拦截器，监听器的功能而无需其他配置，所以这次相中使用到了filter的实现，用到了这个注解。
```

### @MapperScan

```
spring-boot支持mybatis组件的一个注解，通过此注解指定mybatis接口类的路径，即可完成对mybatis接口的扫描。它和@mapper注解是一样的作用，不同的地方是扫描入口不一样。@mapper需要加在每一个mapper接口类上面。所以大多数情况下，都是在规划好工程目录之后，通过@MapperScan注解配置路径完成mapper接口的注入。

添加mybatis相应组建依赖之后。就可以使用该注解。
```

### 资源导入注解

```
资源导入注解：@ImportResource @Import @PropertySource，这三个注解都是用来导入自定义的一些配置文件。

@ImportResource (locations={})导入其他xml配置文件，需要标注在主配置类上，导入property的配置文件@PropertySource指定文件路径，相当于使用spring的<importresource/>标签来完成配置项的引入。

@import注解是一个可以将普通类导入到spring容器中做管理
```

###  Controller 层

```
@RestController 复合注解，相当于@ResponseBody+@Controller合在一起的作用,RestController使用的效果是将方法返回的对象直接在浏览器上展示成json格式.使用该注解，在返回JSON数据到前台时，不需使用@ResponseBody注解。
其实现如下：
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseBody
public @interface RestController {
    @AliasFor(
        annotation = Controller.class
    )
    String value() default "";
}

@Controller：用于定义控制器类，在spring项目中由控制器负责将用户发来的URL请求转发到对应的服务接口（service层），一般这个注解在类中，通常方法需要配合注解@RequestMapping。若需要返回JSON数据到前台，还需要配合@ResponseBody注解
@RequestParam   获取查询参数，用在方法的参数前。即url?name=这种形式
@PathVariable   获取路径参数，用在方法的参数前。即url/{id}这种形式
```

### Servcie层注解

> ```
> @Service：这个注解用来标记业务层的组件，我们会将业务逻辑处理的类都会加上这个注解交给spring容器。事务的切面也会配置在这一层。当然这个注解不是一定要用。有个泛指组件的注解，当我们不能确定具体作用的时候 可以用泛指组件的注解托付给spring容器。 
> 
> 2、@Resource：@Resource和@Autowired一样都可以用来装配bean，都可以标注字段上，或者方法上。 @resource注解不是spring提供的，是属于J2EE规范的注解。
> 两个之前的区别就是匹配方式上有点不同，@Resource默认按照名称方式进行bean匹配，@Autowired默认按照类型方式进行bean匹配。
> 
> ```

### 持久层注解

```
@Repository：@Repository注解类作为DAO对象，管理操作数据库的对象。
@Component, @Service, @Controller, @Repository是spring注解，注解后可以被spring框架所扫描并注入到spring容器来进行管理
@Component是通用注解，其他三个注解是这个注解的拓展，并且具有了特定的功能。用@Service, @Controller, @Repository其中一个标注这个类的定位的时候，就不需要再用@Component来标注
@Transactional： 通过这个注解可以声明事务，可以添加在类上或者方法上。
@Entity：@Table(name=”“)：表明这是一个实体类。一般用于jpa这两个注解一般一块使用，但是如果表名和实体类名相同的话，@Table可以省略
@Column：如果字段名与列名相同，则可以省略。
@Id：表示该属性为主键。
```







## 参考资料

1. http://c.biancheng.net/spring_boot/starter.html
2. https://cloud.tencent.com/developer/article/1493537
3. https://www.jianshu.com/p/56b2535ced58
4. [SpringCloud教程](https://www.bilibili.com/video/BV1f341167hQ?p=3&spm_id_from=pageDriver)
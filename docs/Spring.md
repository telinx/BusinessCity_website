- configuration用于定义配置类，可替换xml配置文件。被注解的类内部包含有一个或多个被@Bean注解的方法，这些方法将会被AnnotationConfigApplicationContext或
AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。

1、注意：
@Configuration注解的配置类有如下要求：

@Configuration不可以是final类型；
@Configuration不可以是匿名类；
嵌套的configuration必须是静态类。

2.Spring DefaultAdvisorAutoProxyCreator类详解


3.spring boot默认的静态页面的位置是：resources/static,默认的动态页面的位置是：resources/templates,
如果自己创建了目录，需要在配置文件中进行指定，spring-boot默认的引擎模版是：thymeleaf,如果不使用thymeleaf，使用
jsp，那么需要进行配置：
```text
#jsp 支持
spring.mvc.view.suffix=.jsp
spring.mvc.view.prefix=/目录
```

4.关于vo,po,dto,do的区分以及使用场景
VO（View Object）：视图对象，用于展示层，它的作用是把某个指定页面（或组件）的所有数据封装起来。

DTO（Data Transfer Object）：数据传输对象，这个概念来源于J2EE的设计模式，原来的目的是为了EJB的分布式应用提供粗粒度的数据实体，以减少分布式调用的次数，从而提高分布式调用的性能和降低网络负载，但在这里，我泛指用于展示层与服务层之间的数据传输对象。

DO（Domain Object）：领域对象，就是从现实世界中抽象出来的有形或无形的业务实体。

PO（Persistent Object）：持久化对象，它跟持久层（通常是关系型数据库）的数据结构形成一一对应的映射关系，如果持久层是关系型数据库，那么，数据表中的每个字段（或若干个）就对应PO的一个（或若干个）属性。
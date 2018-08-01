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
- **项目简介：**

> 项目采用Spring boot作为基础框架，使用Spring boot代替Spring。

> 项目采用前后端分离，前后端分离采用最简单的jquery发送ajax请求，返回json数据的方式，不采用nodejs这种复杂的方式。

> 使用Spring boot ，可以轻松的创建独立运行的程序，非常容易构建独立的服务组件，是实现分布式架构、微服务架构利器。Spring boot简化了第三方包的引用，通过提供的starter，简化了依赖包的配置。

使用Spring boot系统有如下的优点：
- 轻松创建独立的Spring应用程序。
- 内嵌Tomcat、jetty等web容器，不需要手动部署WAR文件。
- 提供一系列的“starter” 来简化的Maven配置。
- 开箱即用，尽可能自动配置Spring。

系统的设计在不断的学习不断的迭代中更新，如果有伙伴愿意贡献建议，非常欢迎～

本工程在github上完全是public的,修改的时候请尽量自己创建分支，然后提交到dev分支上，测试通过之后，提交合并请求～
```text
1、git clone git@github.com:pangjianfei/BusinessCity_website.git
2、git pull
3、git checkout -b 自己的分支名
4、完成自己的代码设计之后，测试无误，git add .,git commit -m "修改信息", git push origin head
```
工程在不断的开发中，不断的添加各个组建

**step1:添加日志打印**
系统使用SLf4J+logback来打印日志，日志集中在一个文件夹中，方便后期的查阅解析。

**ques:**在日志打印配置的时候，考虑一个问题，日志打印应该如何配置最好，是将所有的日志打印到一个日志文件，还是将日志类型进行分离，放置到不同的文件中？
如果是后者，那么应该如何配置？配置请见log.xml

**step2:Spring-boot集成Druid和监控配置**
```text
spring-boot集成了Druid数据源，进行了监控的配置，系统启动之后：访问http://127.0.0.1:8080/druid/index.html：用户名 admin,密码123456即可进行监控
```

**step3:结合shiro进行登录实现**

**step4:实现单点登录（部署单点登录服务端）**

**step5:秒杀实现**

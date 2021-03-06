**Shiro简介**
```text
系统集成了Apache Shiro框架,shiro是一个系统安全框架，shiro强大且易用的Java安全框架,执行身份验证、授权、密码学和会话管理。
Shiro能做什么呢？

验证用户身份
用户访问权限控制，比如：1、判断用户是否分配了一定的安全角色。2、判断用户是否被授予完成某个操作的权限
在非 web 或 EJB 容器的环境下可以任意使用Session API
可以响应认证、访问控制，或者 Session 生命周期中发生的事件
可将一个或以上用户安全数据源数据组合成一个复合的用户 “view”(视图)
支持单点登录(SSO)功能
支持提供“Remember Me”服务，获取用户关联信息而无需登录。

Apache Shiro Features 特性

Apache Shiro是一个全面的、蕴含丰富功能的安全框架。下图为描述Shiro功能的框架图：
http://www.mooooc.com/assets/images/2017/springboot/ShiroFeatures.png


Authentication（认证）, Authorization（授权）, Session Management（会话管理）, Cryptography（加密）被 Shiro 框架的开发团队称之为应用安全的四大基石。那么就让我们来看看它们吧：

Authentication（认证）：用户身份识别，通常被称为用户“登录”
Authorization（授权）：访问控制。比如某个用户是否具有某个操作的使用权限。
Session Management（会话管理）：特定于用户的会话管理,甚至在非web 或 EJB 应用程序。
Cryptography（加密）：在对数据源使用加密算法加密的同时，保证易于使用。
还有其他的功能来支持和加强这些不同应用环境下安全领域的关注点。特别是对以下的功能支持：

Web支持：Shiro 提供的 web 支持 api ，可以很轻松的保护 web 应用程序的安全。
缓存：缓存是 Apache Shiro 保证安全操作快速、高效的重要手段。
并发：Apache Shiro 支持多线程应用程序的并发特性。
测试：支持单元测试和集成测试，确保代码和预想的一样安全。
“Run As”：这个功能允许用户假设另一个用户的身份(在许可的前提下)。
“Remember Me”：跨 session 记录用户的身份，只有在强制需要时才需要登录。
注意： Shiro不会去维护用户、维护权限，这些需要我们自己去设计/提供，然后通过相应的接口注入给Shiro

在概念层，Shiro 架构包含三个主要的理念：Subject,SecurityManager和 Realm。下面的图展示了这些组件如何相互作用，我们将在下面依次对其进行描述。

Subject：当前用户，Subject 可以是一个人，但也可以是第三方服务、守护进程帐户、时钟守护任务或者其它–当前和软件交互的任何事件。
SecurityManager：管理所有Subject，SecurityManager 是 Shiro 架构的核心，配合内部安全组件共同组成安全伞。
Realms：用于进行权限信息的验证，我们自己实现。Realm 本质上是一个特定的安全 DAO：它封装与数据源连接的细节，得到Shiro 所需的相关的数据。在配置 Shiro 的时候，你必须指定至少一个Realm 来实现认证（authentication）和/或授权（authorization）。

首先采用 RBAC模式建立数据库，RBAC 是基于角色的访问控制（Role-Based Access Control ）
```

shiro的认证过程：
```text
Shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo(token)方法。
该方法主要执行以下操作:
1、检查提交的进行认证的令牌信息
2、根据令牌信息从数据源(通常为数据库)中获取用户信息
3、对用户信息进行匹配验证。
4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
5、验证失败则抛出AuthenticationException异常信息。
而在我们的应用程序中要做的就是自定义一个Realm类，继承AuthorizingRealm抽象类，重载doGetAuthenticationInfo
()，重写获取用户信息的方法。
```

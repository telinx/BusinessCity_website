###### Druid数据库连接池由阿里巴巴开源，号称是java语言中最好的数据库连接池，是为监控而生的。
- 关于Druid的常见问题：https://github.com/alibaba/druid/wiki/ 

- Druid强大的监控特性
```text
强大的监控特性
Druid内置了一个功能强大的StatFilter插件可以监控数据库访问性能，可以清楚知道连接池和SQL的工作情况。
监控SQL的执行时间、ResultSet持有时间、返回行数、更新行数、错误次数、错误堆栈信息。
SQL执行的耗时区间分布。什么是耗时区间分布呢？比如说，某个SQL执行了1000次，其中0-1毫秒区间50次
1-10毫秒800次，10-100毫秒100次，100-1000毫秒30次，1-10秒15次，10秒以上5次。通过耗时区间分布，能
非常清楚知道SQL的执行耗时情况。
监控连接池的物理连接创建和销毁次数、逻辑连接的申请和关闭次数、非空等待次数、PSCache命中率等。
```

```text
  <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close"> 
      <!-- 基本属性 url、user、password -->
      <property name="url" value="${jdbc_url}" />
      <property name="username" value="${jdbc_user}" />
      <property name="password" value="${jdbc_password}" />
        
      <!-- 配置初始化大小、最小、最大 -->
      <property name="initialSize" value="1" />
      <property name="minIdle" value="1" /> 
      <property name="maxActive" value="20" />
   
      <!-- 配置获取连接等待超时的时间 -->
      <property name="maxWait" value="60000" />
   
      <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
      <property name="timeBetweenEvictionRunsMillis" value="60000" />
   
      <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
      <property name="minEvictableIdleTimeMillis" value="300000" />
    
      <property name="validationQuery" value="SELECT 'x'" />
      <property name="testWhileIdle" value="true" />
      <property name="testOnBorrow" value="false" />
      <property name="testOnReturn" value="false" />
   
      <!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
      <property name="poolPreparedStatements" value="true" />
      <property name="maxPoolPreparedStatementPerConnectionSize" value="20" />
   
      <!-- 配置监控统计拦截的filters -->
      <property name="filters" value="stat" /> 
  </bean>
```
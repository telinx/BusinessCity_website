**maven的生命周期**

maven的生命周期也是项目的构建过程，包括：

清理、编译、测试、打包、集成测试、验证、部署
```text
       clean 清理项目，包括以下阶段：  　 
           pre-clean 执行清理前
　　　　　　clean	清理上一次构建生成的所有文件
　　　　　　post-clean	执行清理后的文件
　　　　default 构建项目(核心：常用)，包括以下阶段
　　　　　　compile 编译
　　　　　　test 测试
　　　　　　packeage 打包
　　　　　　install 安装
　　　　site 生成项目站点,根据pom中信息生成项目站点，包括以下阶段
　　　　　　pre-site 在生成项目站点前要完成的工作
　　　　　　site生成项目的站点文档
　　　　　　post-site在生成项目站点后要完成的工作
　　　　　　site-deploy发布生成的站点到服务器上
```


**maven scope的分类以及作用**

- 1.compile：默认值 他表示被依赖项目需要参与当前项目的编译，还有后续的测试，运行周期也参与其中，是一个比较强的依赖。打包的时候通常需要包含进去
- 2.test：依赖项目仅仅参与测试相关的工作，包括测试代码的编译和执行，不会被打包，例如：junit
- 3.runtime：表示被依赖项目无需参与项目的编译，不过后期的测试和运行周期需要其参与。与compile相比，跳过了编译而已。例如JDBC驱动，适用运行和测试阶段
- 4.provided：打包的时候可以不用包进去，别的设施会提供。事实上该依赖理论上可以参与编译，测试，运行等周期。相当于compile，但是打包阶段做了exclude操作
- 5.system：从参与度来说，和provided相同，不过被依赖项不会从maven仓库下载，而是从本地文件系统拿。需要添加systemPath的属性来定义路径

**maven常用命令**

- mvn archetype:create -DgroupId=packageName -DartifactId=webappName -DarchetypeArtifactId=maven-archetype-weapp  >  创建web工程
- mvn clean  清理
- mvn compile  
- mvn test-compile
- mvn test
- mvn package 打包
- mvn tomcat7:run  运行在tomcat上
- mvn validate  验证工程是否正确
- mvn install -Dmaven.test.skip=true 跳过测试进行安装
- mvn dependency:resolve  打印出已经解决的依赖列表
- mvn dependency:tree 打印整个依赖树


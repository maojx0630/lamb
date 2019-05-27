# lamb
## nacos启动
* https://github.com/alibaba/nacos/releases 下载稳定版本  
* 在mysql数据库中创建nacos库名  
* 将conf中的nacos-mysql.sql文件导入
* 修改application.properties加入
```
db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect
=true
db.user=root
db.password=root
```
* 在bin目录中使用命令以单机模式启动
```
sh startup.sh -m standalone
```
* 启动成功后即可通过8848端口访问  
* k8s部署详看文档 https://nacos.io/zh-cn/docs/use-nacos-with-kubernetes.html
## sentinel-dashboard启动
* https://github.com/alibaba/Sentinel/releases 下载稳定版本  
* 通过如下命令启动
```
java -Dserver.port=8888 -Dcsp.sentinel.dashboard.server=localhost:8888 -Dproject.name=sentinel-dashboard -jar 
sentinel-dashboard.jar
```
* 启动后即可通过浏览器访问端口号为8888
## tx-lcn分布式事物协调器启动
* https://github.com/codingapi/tx-lcn/releases/tag/5.0.2.RELEASE 下载稳定版本  
* 修改txlcn-tm子项目下的application.properties配置文件 mysql与redis
* 手动使用 ```mvn clean  package '-Dmaven.test.skip=true'``` 命令打包
* 以spring boot配置方式启动即可
* 使用方法
	* 启动类增加 ```@EnableDistributedTransaction```
	* 在发起者使用 ```@Transactional``` ```@LcnTransaction```
	* 被调用者使用 ```@Transactional```  
	```@LcnTransaction(propagation= DTXPropagation.SUPPORTS)```
## graylog分布式日志收集
* 前置需求es与mongodb graylog安装方法不赘述
* 引入依赖
```xml
<dependency>
    <groupId>de.siegmar</groupId>
    <artifactId>logback-gelf</artifactId>
    <version>2.0.0</version>
</dependency>
```
* 在resources目录下添加 logback.xml
```xml
<configuration>

    <appender name="GELF" class="de.siegmar.logbackgelf.GelfUdpAppender">
        <graylogHost>localhost</graylogHost>
        <graylogPort>12201</graylogPort>
        <maxChunkSize>508</maxChunkSize>
        <useCompression>true</useCompression>
        <encoder class="de.siegmar.logbackgelf.GelfEncoder">
            <originHost>localhost</originHost>
            <includeRawMessage>false</includeRawMessage>
            <includeMarker>true</includeMarker>
            <includeMdcData>true</includeMdcData>
            <includeCallerData>false</includeCallerData>
            <includeRootCauseData>false</includeRootCauseData>
            <includeLevelName>false</includeLevelName>
            <shortPatternLayout class="ch.qos.logback.classic.PatternLayout">
                <pattern>%m%nopex</pattern>
            </shortPatternLayout>
            <fullPatternLayout class="ch.qos.logback.classic.PatternLayout">
                <pattern>%m%n</pattern>
            </fullPatternLayout>
            <staticField>app_name:backend</staticField>
            <staticField>os_arch:${os.arch}</staticField>
            <staticField>os_name:${os.name}</staticField>
            <staticField>os_version:${os.version}</staticField>
        </encoder>
    </appender>

    <root level="debug">
        <appender-ref ref="GELF" />
    </root>

</configuration>
```
* 插件地址 https://github.com/osiegmar/logback-gelf
## 链路监控skywalking
* http://skywalking.apache.org/downloads/ 下载 前置需求es
* 解压后 修改webapp/webapp.yml 的port为 10800 (避免占用8080端口,若用docker启动也可不更改直接-p映射即可)
* 修改 config/application.yml 的storage 将h2注释  将es部分注释打开配置正确的clusterNodes地址
* 如果直接启动 可以 进入bin目录  sh startup.sh启动即可 
* 若docker启动则手动打包为docker镜像 命令需要额外 tail -f 防止自动关闭
* 运行 docker run -d -p  10800:10800 -p 11800:11800 -p 12800:12800  --name skywalking skywalking  
(如果没有修改webapp端口则 10800:8080)
* 客户端使用方法:  
启动每个服务时加上以下虚拟机参数, javaagent配置绝对路径, Dskywalking.agent.service_name 对应服务名
```
-javaagent:/lamb/SkyWalking/skywalking-agent.jar
-Dskywalking.agent.service_name=nacos-provider
-Dskywalking.collector.backend_service=localhost:11800
```
## 关于config目录
该目录放置用于存放在nacos的配置文件
## 关于common包
common包均采用自动装配,具体装配策略详见spring.factories
## 关于权限管理 推荐使用vue动态路由+springAnt规则校验  
```org.springframework.util.AntPathMatcher```
## mybatis分页工具采用我自己写的paging 也可根据自己需要引入
## 推荐mybatis的idea插件 
```MyBatis Log Plugin ```  
```MyBatisCodeHelperPro```
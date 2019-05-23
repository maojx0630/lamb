#lamb
##nacos启动
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
	* 在发起者使用 ```@Transactional``` ```@LcnTransaction```
	* 被调用者使用 ```@Transactional```  
	```@LcnTransaction(propagation= DTXPropagation.SUPPORTS)```
##关于config目录
该目录放置用于存放在nacos的配置文件
##关于common包
common包均采用自动装配,具体装配策略详见spring.factories
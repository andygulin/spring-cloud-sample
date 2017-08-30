#### Spring Cloud Sample

##### 模块
    spring-cloud-sample-common          公共模块
    spring-cloud-config-server          配置中心
    spring-cloud-sample-eureka-server   服务中心
    spring-cloud-sample-userservice     服务提供者
    spring-cloud-sample-consumer        服务消费者
    
##### 运行
    mvn clean package

###### 启动配置中心
    java -jar spring-cloud-sample-config-server/target/spring-cloud-sample-config-server-1.0.jar

###### 启动服务中心
    修改hosts文件
    127.0.0.1 peer1 peer2
    java -jar spring-cloud-sample-eureka-server/target/spring-cloud-sample-eureka-server-1.0.jar --spring.profiles.active=peer1
    java -jar spring-cloud-sample-eureka-server/target/spring-cloud-sample-eureka-server-1.0.jar --spring.profiles.active=peer2
    
###### 查看服务中心状态
    http://peer1:1111
    http://peer2:1112
    用户名：admin
    密码：admin
    
###### 启动服务提供者，可以启动多个用于负载
    java -jar spring-cloud-sample-userservice/target/spring-cloud-sample-userservice-1.0.jar --server.port=2222
    java -jar spring-cloud-sample-userservice/target/spring-cloud-sample-userservice-1.0.jar --server.port=2223
    java -jar spring-cloud-sample-userservice/target/spring-cloud-sample-userservice-1.0.jar --server.port=2224
    java -jar spring-cloud-sample-userservice/target/spring-cloud-sample-userservice-1.0.jar --server.port=2225
    
###### 测试服务消费者
    java -jar spring-cloud-sample-consumer/target/spring-cloud-sample-consumer-1.0.jar
    
    http://localhost:8080/service/user/all
    http://localhost:8080/service/user/get/1
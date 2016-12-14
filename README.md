#### Spring Cloud Sample

##### 模块
    spring-cloud-sample-common          公共模块
    spring-cloud-sample-eureka-server   服务中心
    spring-cloud-sample-userservice     服务提供者
    spring-cloud-sample-consumer        服务消费者
    
##### 运行
    mvn clean package
    
###### 启动服务中心
    java -jar spring-cloud-sample-eureka-server/target/*.jar --server.port=1111
###### 查看服务中心状态
    http://localhost:1111
###### 启动服务提供者，可以启动多个用于负载
    java -jar spring-cloud-sample-userservice/target/*.jar --server.port=2222
    java -jar spring-cloud-sample-userservice/target/*.jar --server.port=2223
    java -jar spring-cloud-sample-userservice/target/*.jar --server.port=2224
    java -jar spring-cloud-sample-userservice/target/*.jar --server.port=2225
###### 启动服务消费者
    java -jar spring-cloud-sample-consumer/target/*.jar --server.port=3333
###### 测试
    http://localhost:3333/consumer/get/1
    http://localhost:3333/consumer/all
#### Spring Cloud Sample

##### 模块
    spring-cloud-sample-common          公共模块
    spring-cloud-sample-eureka-server   服务中心
    spring-cloud-sample-userservice     服务提供者
    spring-cloud-sample-consumer        服务消费者
    
##### 运行
    mvn clean package -Dmaven.test.skip=true
    
###### 启动服务中心
    java -jar spring-cloud-sample-eureka-server/target/spring-cloud-sample-eureka-server-1.0.jar --server.port=1111
###### 查看服务中心状态
    http://localhost:1111
###### 启动服务提供者，可以启动多个用于负载
    java -jar spring-cloud-sample-userservice/target/spring-cloud-sample-userservice-1.0.jar --server.port=2222
    java -jar spring-cloud-sample-userservice/target/spring-cloud-sample-userservice-1.0.jar --server.port=2223
    java -jar spring-cloud-sample-userservice/target/spring-cloud-sample-userservice-1.0.jar --server.port=2224
    java -jar spring-cloud-sample-userservice/target/spring-cloud-sample-userservice-1.0.jar --server.port=2225
###### 测试服务消费者
    mvn test -Dtest=spring.cloud.sample.ConsumerTest#all -DfailIfNoTests=false
    mvn test -Dtest=spring.cloud.sample.ConsumerTest#get -DfailIfNoTests=false
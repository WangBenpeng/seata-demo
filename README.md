# seata-demo
a demo for spring-cloud-seata

#本项目依据[spring-cloud-alibaba-seata-example](https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/seata-example/readme-zh.md) 改造而成

## 1.前置条件
- nacos:服务发现 [点击下载](https://github.com/alibaba/nacos/releases)
- seata:分布式事务 [点击下载](https://github.com/seata/seata/releases)

## 2. 项目结构
- account-service：账户服务
- business-service：交易服务
- common-service：公共实体
- order-service：订单服务
- storage-service：库存服务

## 3.项目启动
1. 把seata-demo/springcloud-demo/sql/mysql.sql导入数据库
2. 本地运行nacos
    ```shell script
    sh nacos/bin/startup.sh -m standalone
    ```
3. 本地运行seata
    ```shell script
    sh seata/bin/seata-server.sh -p 8091 -m file
    ```
4. 启动顺序

    account-service --> storage-service --> order-service --> business-service
    
5. restful接口访问

    http://localhost:18001/seata/rest
  
   feign接口访问
   
   http://localhost:18001/seata/feign
# inflearn-e-commerce-msa

<br>

## 🔎️ 선행 실행 프로그램
in windows Powershell
- RabbitMQ Server
  - 실행
    ```bash
    rabbitmq-server -detached 
    ```
  - 종료
    ```bash
    cd 'C:\Program Files\RabbitMQ Server\rabbitmq_server-3.12.8\sbin'
    ```
    ```bash
    rabbitmqctl stop
    ```


- Zookeeper
  - 실행
    ```bash
    cd 'C:\kafka\kafka_2.13-3.6.0'
    ```
    ```bash
    .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
    ```


- Kafka Server
  - 실행
    ```bash
    cd 'C:\kafka\kafka_2.13-3.6.0'
    ```
    ```bash
    .\bin\windows\kafka-server-start.bat .\config\server.properties
    ```


- Kafka Connector
  - 실행
    ```bash
    cd 'c:\kafka\confluent-7.5.2'
    ```
    ```bash
    .\bin\windows\connect-distributed.bat .\etc\kafka\connect-distributed.properties
    ```

<br>

## ✏️ Spring Boot 프로젝트 환경
- spring boot 3.1.5  
- java 17  
- jdk 19 <br><br>  

### ecommerce (service discovery)
- port 
    - 8761  <br><br>  
- dependency   
    - Eureka Server  
    - Spring Boot Actuator

<br>

### api-gateway-service  ( gateway )
- port 
    - 8000 <br><br>  
- dependency   
    - Eureka Discovery Client  
    - Gateway
    - Cloud Bootstrap
    - Spring Boot Actuator
    - Spring Cloud Starter Bus Amqp <br><br>
    - Lombok
    - jjwt  

<br>

### config-service  ( config )
> 설정 파일 git repository 🔗 [inflearn-e-commerce-msa-config](https://github.com/zhyunk/inflearn-e-commerce-msa-config)

- port 
    - 8888 <br><br>  
- dependency   
    - config server
    - Cloud Bootstrap
    - Spring Boot Actuator
    - Spring Cloud Starter Bus Amqp

<br>

### user-service 
- port 
    - random  <br><br>  
- dependency   
    - Eureka Discovery Client  
    - Config Client
    - Cloud Bootstrap
    - Spring Boot Actuator
    - Spring Cloud Starter Bus Amqp
    - Spring Boot DevTools   <br><br>
    - Spring Web  
    - OpenFeign
    - Lombok  
    - jpa 
    - h2 
    - validation
    - model mapper 
    - security
    - jjwt  

<br>

### catalog-service 
- port 
    - random  <br><br>  
- dependency   
    - Eureka Discovery Client  
    - Cloud Bootstrap
    - Spring Boot Actuator
    - Spring Cloud Starter Bus Amqp
    - Spring Boot DevTools   <br><br>
    - Spring Web  
    - Lombok  
    - jpa 
    - h2 
    - validation
    - model mapper 
    
<br>

### order-service 
- port 
    - random  <br><br>  
- dependency   
    - Eureka Discovery Client  
    - Cloud Bootstrap
    - Spring Boot Actuator
    - Spring Cloud Starter Bus Amqp
    - Spring Boot DevTools   <br><br>
    - Spring Web  
    - Lombok  
    - jpa 
    - h2 
    - mariadb
    - validation
    - model mapper 

<br>
<br>

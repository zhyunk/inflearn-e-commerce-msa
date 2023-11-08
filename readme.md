# inflearn-e-commerce-msa


## ✏️ 프로젝트 환경

### ecommerce (service discovery)
- 실행 port = 8761  <br><br>  
- spring boot 3.1.5  
- java 17  
- jdk 19 <br><br>  
- dependency   
    - Eureka Server  

<br>

### api-gateway-service  ( gateway )
- 실행 port = 8000 <br><br>  
- spring boot 3.1.5  
- java 17  
- jdk 19 <br><br>  
- dependency   
    - Eureka Discovery Client  
    - Lombok
    - Gateway

<br>

### user-service 
- 실행 port = random  <br><br>  
- spring boot 3.1.5  
- java 17  
- jdk 19 <br><br>  
- dependency   
    - Eureka Discovery Client  
    - Spring Boot DevTools  
    - Lombok  
    - Spring Web  
    - jpa 
    - h2 
    - validation
    - model mapper 
    - security

<br>
<br>

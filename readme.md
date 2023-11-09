# inflearn-e-commerce-msa


## ✏️ 프로젝트 환경
- spring boot 3.1.5  
- java 17  
- jdk 19 <br><br>  

### ecommerce (service discovery)
- port 
    - 8761  <br><br>  
- dependency   
    - Eureka Server  

<br>

### api-gateway-service  ( gateway )
- port 
    - 8000 <br><br>  
- dependency   
    - Eureka Discovery Client  
    - Lombok
    - Gateway

<br>

### user-service 
- port 
    - random  <br><br>  
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

### catalog-service , order-service 
- port 
    - random  <br><br>  
- dependency   
    - Eureka Discovery Client  
    - Spring Boot DevTools  
    - Lombok  
    - Spring Web  
    - jpa 
    - h2 
    - validation
    - model mapper 

<br>
<br>

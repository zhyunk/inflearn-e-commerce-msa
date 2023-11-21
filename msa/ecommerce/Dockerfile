FROM openjdk:19-ea-jdk-slim
VOLUME /tmp
COPY build/libs/ecommerce-0.0.1.jar DiscoveryService.jar
ENTRYPOINT ["java", "-jar", "DiscoveryService.jar"]

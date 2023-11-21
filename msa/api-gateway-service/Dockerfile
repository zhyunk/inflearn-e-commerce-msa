FROM openjdk:19-ea-jdk-slim
VOLUME /tmp
COPY build/libs/api-gateway-service-0.0.1.jar ApigatewayService.jar
ENTRYPOINT ["java", "-jar", "ApigatewayService.jar"]

FROM openjdk:19-ea-jdk-slim
VOLUME /tmp
COPY build/libs/user-service-0.0.1.jar UserService.jar
ENTRYPOINT ["java", "-jar", "UserService.jar"]

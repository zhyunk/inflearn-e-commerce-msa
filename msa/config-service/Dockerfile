FROM openjdk:19-ea-jdk-slim
VOLUME /tmp
COPY apiEncryptionKey.jks apiEncryptionKey.jks
COPY build/libs/config-service-0.0.1.jar ConfigServer.jar
ENTRYPOINT ["java", "-jar", "ConfigServer.jar"]

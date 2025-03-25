FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar gateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "gateway.jar"]

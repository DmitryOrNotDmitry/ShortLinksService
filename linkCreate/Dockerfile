FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar createLink.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "createLink.jar"]

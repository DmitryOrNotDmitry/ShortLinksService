FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar redirectService.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "redirectService.jar"]

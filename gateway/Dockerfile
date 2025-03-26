FROM openjdk:21-jdk-slim

RUN apt update && apt install -y curl

WORKDIR /app

COPY target/*.jar gateway.jar

ENTRYPOINT ["java", "-Dservices.linkcreate=http://link-shortener-service", "-Dservices.redirect=http://redirecting-service", "-jar", "gateway.jar"]

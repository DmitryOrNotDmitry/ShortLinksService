FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar createLink.jar

ENTRYPOINT ["java", "-Ddb.url=jdbc:postgresql://host.minikube.internal:5432/links_db", "-jar", "createLink.jar"]

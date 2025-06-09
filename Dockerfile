FROM maven:3.8-eclipse-temurin-17-alpine as build
WORKDIR /app
COPY /target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
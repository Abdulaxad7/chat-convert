FROM openjdk:22-jdk-slim

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src

RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests

EXPOSE 9090

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
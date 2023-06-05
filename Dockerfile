### build stage
FROM maven:3.6.3-jdk-11-slim AS build

WORKDIR usr/src/app

COPY . ./

RUN mvn clean package -Dmaven.test.skip

### package stage
FROM openjdk:11-jre-slim

ARG JAR_NAME=crypto-1.0.jar

WORKDIR /apps

EXPOSE 8080

COPY --from=build /usr/src/app/target/${JAR_NAME} app.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/apps/app.jar"]
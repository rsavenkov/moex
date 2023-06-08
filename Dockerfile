### build stage
FROM maven:3.6.3-jdk-11-slim AS build

WORKDIR usr/src/app

COPY . ./

RUN mvn clean package -Dmaven.test.skip

### package stage
FROM openjdk:11-jre-slim

WORKDIR /apps

EXPOSE 8080

COPY --from=build /usr/src/app/target/test-1.0.jar app.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/apps/app.jar"]
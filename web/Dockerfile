FROM maven:3.8.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn clean package -DskipTests
RUN ls -l target

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

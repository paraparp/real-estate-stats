FROM maven:3.9.2-amazoncorretto-20 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:20-jdk-slim
COPY --from=build /target/realStateStats-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar","realStateStats-0.0.1-SNAPSHOT.jar"]
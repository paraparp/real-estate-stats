FROM maven:3.9.2-amazoncorretto-20 AS builder
WORKDIR /
COPY . /
RUN mvn dependency:go-offline
RUN mvn package

FROM openjdk:20-jdk-slim
WORKDIR /
COPY --from=builder /target/app.jar .
CMD ["java", "-jar", "app.jar"]
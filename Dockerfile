FROM maven:3.9.2-amazoncorretto-20 AS builder
WORKDIR /app
COPY . /app
RUN mvn dependency:go-offline
RUN mvn package

FROM openjdk:20-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/app.jar .
CMD ["java", "-jar", "app.jar"]
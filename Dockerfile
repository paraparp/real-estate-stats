# Build stage
FROM maven:3.9.0-jdk-20 AS build
COPY . .
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:20-jdk
COPY --from=build /target/realStateStats-0.0.1-SNAPSHOT.jar realStateStats.jar
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "realStateStats.jar"]

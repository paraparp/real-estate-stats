# Build stage
FROM openjdk:20-jdk AS build
COPY . .
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:20-jdk
COPY --from=build /target/realStateStats-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


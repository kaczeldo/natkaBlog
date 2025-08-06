# Start from a Maven image to build the app
FROM maven:3.8.7-eclipse-temurin-19 AS build


# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies (cache optimization)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy all source code
COPY src ./src

# Build the Spring Boot jar
RUN mvn clean package -DskipTests

# Use a lightweight OpenJDK image to run the app
FROM eclipse-temurin:17-jre-alpine

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on (default 8080)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
# Builder stage
FROM gradle:8.4-jdk17-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the application without running tests
# Skipping tests can be done in CI/CD pipelines, but it's recommended to enable them in production builds
RUN gradle build --no-daemon -x test

# Final stage
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR from the builder stage to the final stage
# Since you have set the JAR name to be dynamic, this will handle it correctly
COPY --from=builder /app/build/libs/*SNAPSHOT.jar app.jar

# Expose the application's port (default is 8080 for Spring Boot)
EXPOSE 8080

# Define volumes for any external data or secrets
VOLUME /home/ite/media
VOLUME /keys   

# Define the entry point for the application
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]

# Use the official Kotlin image as the base image
FROM gradle:8.4-jdk21 AS build

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the application
RUN gradle build -x test

# Use a lightweight JDK image for the runtime
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built application from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
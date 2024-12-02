# Use an OpenJDK image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the application's JAR file to the container
COPY build/libs/notification-hub.jar notification-hub.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "notification-hub.jar"]
# Use the official OpenJDK image as a base
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from your local machine to the container
COPY target/sb-auth-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

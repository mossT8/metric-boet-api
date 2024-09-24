# Use the official OpenJDK image as a base
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY ./backend/target/api-0.0.1-SNAPSHOT.jar ./metric-boet-api.jar

ENTRYPOINT ["java", "-jar", "./metric-boet-api.jar"]

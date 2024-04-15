# Use a base image with Java 17
FROM openjdk:17-slim

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Specify the entrypoint to run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]

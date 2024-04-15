# Use Eclipse Temurin official image
FROM eclipse-temurin:latest

# Set working directory inside the container
WORKDIR /app

# Copy the Java application's executable jar file into the container
COPY ./my-java-app.jar /app

# Command to run the Java application
CMD ["java", "-jar", "my-java-app.jar"]

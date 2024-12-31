# Use an official Java 17 runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container at /app
ARG JAR_FILE=target/service1-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Make port 8083 available to the world outside this container
EXPOSE 8083

# Run the jar file
ENTRYPOINT ["java","-jar","/app/app.jar"]


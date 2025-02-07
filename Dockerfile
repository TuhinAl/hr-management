#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
##WORKDIR /app
#
## Copy the jar file into the container
#COPY ./target/employeemanagement-0.0.1-SNAPSHOT.jar app.jar
#
##EXPOSE 8080
#
## Specify the command to run on container start
#ENTRYPOINT ["java", "-jar", "/app.jar"]

# Use a base image with OpenJDK
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/employeemanagement-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
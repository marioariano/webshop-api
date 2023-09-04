# Use the official AdoptOpenJDK base image
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/vendaprodutos-0.0.1-SNAPSHOT.jar /app/vendaprodutos-0.0.1-SNAPSHOT.jar

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "vendaprodutos-0.0.1-SNAPSHOT.jar"]


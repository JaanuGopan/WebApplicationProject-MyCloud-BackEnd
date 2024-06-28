FROM openjdk:21

# Install netcat
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

# Add the backend application jar
ADD target/devops-project-backend.jar devops-project-backend.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/devops-project-backend.jar"]

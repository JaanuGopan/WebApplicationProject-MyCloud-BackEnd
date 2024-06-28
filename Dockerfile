FROM openjdk:21

# Install netcat
#RUN apk add --no-cache netcat-openbsd

# Add the backend application jar
ADD target/devops-project-backend.jar devops-project-backend.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/devops-project-backend.jar"]

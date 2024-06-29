FROM openjdk:21
ADD target/devops-project-backend.jar devops-project-backend.jar
ENTRYPOINT ["java", "-jar", "/devops-project-backend.jar"]

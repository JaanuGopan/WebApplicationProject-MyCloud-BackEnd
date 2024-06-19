FROM openjdk:17
ADD devops-project-backend.jar devops-project-backend.jar
ENTRYPOINT ["java", "-jar", "/devops-project-backend.jar"]

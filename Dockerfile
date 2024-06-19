FROM openjdk:17
ADD target/cloudstoragemanagement-0.0.1-SNAPSHOT.jar cloudstoragemanagement-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/cloudstoragemanagement-0.0.1-SNAPSHOT.jar"]

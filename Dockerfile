FROM amazoncorretto:21-alpine-jdk

COPY target/spring-boot-thymeleaf-example-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["java", "-jar" ,"/app.jar"]

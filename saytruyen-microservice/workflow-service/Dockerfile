FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY ./target/workflow-service-1.0-SNAPSHOT.jar workflow-service.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "workflow-service.jar"]
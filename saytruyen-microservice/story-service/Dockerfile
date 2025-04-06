FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY ./target/story-service-1.0-SNAPSHOT.jar story-service.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "story-service.jar"]
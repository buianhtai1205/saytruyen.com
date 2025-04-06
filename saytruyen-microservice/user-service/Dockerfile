FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY ./target/user-service-1.0-SNAPSHOT.jar user-service.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "user-service.jar"]
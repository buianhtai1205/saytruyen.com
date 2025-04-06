FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY ./target/admin-service-1.0-SNAPSHOT.jar admin-service.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "admin-service.jar"]
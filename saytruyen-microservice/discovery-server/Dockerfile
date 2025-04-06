FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY ./target/discovery-server-1.0-SNAPSHOT.jar discovery-server.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]
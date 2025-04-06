FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY ./target/api-gateway-1.0-SNAPSHOT.jar api-gateway.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
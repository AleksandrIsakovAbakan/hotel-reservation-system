FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY build/libs/hotel-reservation-system-0.0.1-SNAPSHOT.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend.jar"]

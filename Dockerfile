FROM openjdk:17.0.2-slim

EXPOSE 8080

RUN mkdir /app

COPY build/libs/*.jar /app/spring-boot-application.jar
COPY src/main/resources/application.yml /app/
COPY city.list.json city.list.json

CMD ["java", "-jar", "/app/spring-boot-application.jar"]
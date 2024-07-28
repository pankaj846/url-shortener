
FROM openjdk:18

WORKDIR /app

COPY ./target/urlshortener-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "urlshortener-0.0.1-SNAPSHOT.jar"]
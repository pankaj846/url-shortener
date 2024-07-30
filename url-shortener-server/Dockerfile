# Base image: Fetching latest version of Java
FROM openjdk:18

# sets the working directory inside the container to /app
WORKDIR /app

# Copy the jar file to the /app directory inside the container
COPY ./target/urlshortener-0.0.1-SNAPSHOT.jar /app

# container will use port 8080. However still need to use the -p option when running the container for port mapping
EXPOSE 8080

# When container starts, use below command "java -jar demo.app-0.0.1-SNAPSHOT.jar"
ENTRYPOINT ["java", "-jar", "urlshortener-0.0.1-SNAPSHOT.jar"]
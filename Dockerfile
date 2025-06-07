# Use a Java 17 image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./
COPY src/ src/
RUN ./mvnw package -DskipTests
ENTRYPOINT ["java","-jar","target/pour-0.0.1-SNAPSHOT.jar"]
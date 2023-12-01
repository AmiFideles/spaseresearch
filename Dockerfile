FROM maven:3.8.7-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/spaceresearch-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
CMD ["java", "-jar", "spaceresearch-0.0.1-SNAPSHOT.jar"]

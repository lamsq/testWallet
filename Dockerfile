#base image
FROM openjdk:17-jdk-slim

#sets the working directory
WORKDIR /app

#copies maven build file and install dependencies
COPY pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn
RUN ./mvnw dependency:resolve

#copies the source code to the container
COPY src ./src

#package the application
RUN ./mvnw package -DskipTests

#exposes the port
EXPOSE 8080

#runs the application
ENTRYPOINT ["java", "-jar", "target/wallet-0.0.1-SNAPSHOT.jar"]

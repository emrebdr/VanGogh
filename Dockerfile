FROM maven:3.8.6-eclipse-temurin-17-alpine
WORKDIR /app
COPY . .
RUN mvn clean package assembly:single -DskipTests

ENTRYPOINT java -jar target/VanGogh.jar
FROM openjdk:22-jdk-slim as builder
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

WORKDIR /app
COPY . /app
RUN mvn clean install -DskipTests

FROM openjdk:22-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/user-0.0.1.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

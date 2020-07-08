FROM openjdk:8-jdk-alpine
WORKDIR /app
ADD ./target/springboot-demo-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","springboot-demo-0.0.1-SNAPSHOT.jar"]
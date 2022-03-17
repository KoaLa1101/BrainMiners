FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/brainMiners-1.0.jar brainMiners-1.0.jar
ENTRYPOINT ["java","-jar","/brainMiners-1.0.jar"]
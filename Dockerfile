FROM openjdk:8-jdk-alpine
COPY --from=build /home/app/target/brainMiners-1.0.jar /usr/local/lib/app.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
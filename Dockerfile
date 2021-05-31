FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/backend-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/backend-0.0.1-SNAPSHOT.jar"]
FROM amazoncorretto:11-alpine-jdk
MAINTAINER LUCIANO RODRIGUEZ
COPY target/lucianorodriguez-0.0.1-SNAPSHOT.jar lucianorodriguez-app-jar
ENTRYPOINT ["java","-jar","/lucianorodriguez-app-jar"]

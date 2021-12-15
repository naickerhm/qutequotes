FROM debian:latest 
FROM openjdk:11

ADD server/libs/server-1.0-SNAPSHOT.jar /srvr/libs/server.jar
ADD server/src /srvr/src
ADD server/libs /srvr/libs
ADD server/qutequotes.db /srvr
WORKDIR /srvr
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "libs/server.jar"]

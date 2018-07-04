FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/discoveryServer
ADD @project.build.finalName@.jar /usr/local/discoveryServer/
ADD run.sh /usr/local/run.sh
RUN chmod +x /usr/local/run.sh
CMD sh /usr/local/run.sh
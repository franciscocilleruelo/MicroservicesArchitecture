#!/bin/sh
echo "********************************************************"
echo "Starting the Eureka Server"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE -Deureka.serviceUrl.defaultZone=$EUREKASERVER_URI -Dspring.cloud.config.uri=$CONFIGSERVER_URI -jar /usr/local/discoveryServer/@project.build.finalName@.jar

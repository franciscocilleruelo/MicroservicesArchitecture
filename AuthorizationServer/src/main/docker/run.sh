#!/bin/sh
echo "********************************************************"
echo "Waiting for the discovery server to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z discoveryserver $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Discovery Server has started"

echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z configserver $CONFIGSERVER_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"


echo "********************************************************"
echo "Starting Authentication Service                           "
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI             \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI -Dspring.profiles.active=$PROFILE              \
     -jar /usr/local/authorizationServer/@project.build.finalName@.jar

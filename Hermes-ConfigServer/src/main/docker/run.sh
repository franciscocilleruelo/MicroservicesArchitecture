#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the discovery server to start on port $(getPort $EUREKASERVER_PORT)"
echo "********************************************************"
while ! `nc -z discoveryserver  $(getPort $EUREKASERVER_PORT)`; do sleep 3; done
echo "******* Discovery Server has started"

echo "********************************************************"
echo "Starting Configuration Service with Eureka Endpoint:  $EUREKASERVER_URI";
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI -Dspring.profiles.active=$PROFILE -jar /usr/local/configServer/@project.build.finalName@.jar

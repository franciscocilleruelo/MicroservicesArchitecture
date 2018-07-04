package com.cilleruelo.microservices.configServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Main configuration server class
 * Endpoint to get the properties about some specific service registered on the configuration server: http://<configurationService>:8888/<serviceName>/<profile>
 * For example, http://localhost:8888/Invoices-RestServices/dev
 * 
 * @author francisco.cilleruelo
 *
 */
@SpringBootApplication
@EnableDiscoveryClient // It allows to be registered on the discovery server and gets location info about other components registered on it
@EnableConfigServer
public class ConfigServerApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
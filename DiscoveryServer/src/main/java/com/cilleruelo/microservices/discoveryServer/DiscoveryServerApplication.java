package com.cilleruelo.microservices.discoveryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Main Discovery server class
 * Endpoint To get the full info about some specific service registered on Eureka: http://{host}:{port}/eureka/apps/{serviceName}
 * For example, http://localhost:8761/eureka/apps/Invoices-RestServices 
 * 
 * @author francisco.cilleruelo
 *
 */
@SpringBootApplication
@EnableEurekaServer
// 
// 
public class DiscoveryServerApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }

}

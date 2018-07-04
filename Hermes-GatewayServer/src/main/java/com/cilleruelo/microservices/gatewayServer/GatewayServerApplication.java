package com.cilleruelo.microservices.gatewayServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Main gateway server class
 * Endpoint to get mappings about the routes are managed by the gateway server: http://{host}:{port}/routes
 * Way to call a specific service by the gateway server will be by the URL format: http://{host}:{port}/{serviceName}/{restUrl}
 * For example, http://localhost:5555/Invoices-RestServices/invoices
 * 
 * @author francisco.cilleruelo
 *
 */
@SpringBootApplication
@EnableZuulProxy
@RefreshScope // It allows to refresh the properties have changed on Git by calling the endpoint: http://{host}:{port}/refresh
public class GatewayServerApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

}

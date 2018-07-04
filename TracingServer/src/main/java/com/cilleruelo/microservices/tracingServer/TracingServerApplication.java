package com.cilleruelo.microservices.tracingServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import zipkin.server.EnableZipkinServer;

/**
 * Tracing server main class
 * URL to access the tracing server UI: http://{host}:port
 * 
 * @author francisco.cilleruelo
 *
 */
@SpringBootApplication
@EnableZipkinServer 
@RefreshScope // It allows to refresh the properties have changed on Git by calling the endpoint: http://{host}:{port}/refresh
public class TracingServerApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(TracingServerApplication.class, args);
    }

}
